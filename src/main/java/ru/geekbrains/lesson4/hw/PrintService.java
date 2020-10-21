package ru.geekbrains.lesson4.hw;

import java.math.BigDecimal;
import java.util.*;

public class PrintService {
    enum printSchemas {
        schema1, schema2, schema3, schema4
    }
    enum printColumns {
        movie_name, allQuantitiesForMovie, avgQuantitiesForMovieSession, totalRevenueForMovie
    }

    private final RequestManager requestManager;
    private final Map<String, Object> printMap = new HashMap<>();
    private final List<Integer> ids;

    public PrintService() {
        this.requestManager =  new RequestManager();
        this.ids = requestManager.getMoviesIdList();
    }

    public void print(PrintService.printSchemas printSchema) {

        if(printSchema.equals(printSchemas.schema3)) {
            ids.forEach(i -> {
                List<HashMap<String, Object>> sessions = requestManager.getSessionsJoinMoviesAndTicketsByMovieId(i);
                Set<Integer> sessionsIds = sessionsIds(sessions);
                int allQuantities = sessions.stream().mapToInt(s -> (int) s.get(RequestManager.columnLabels.quantity.name())).sum();

                printMap.put(printColumns.movie_name.name(), sessions.get(0).get(RequestManager.columnLabels.movie_name.name()));
                printMap.put(printColumns.allQuantitiesForMovie.name(), allQuantities);
                printMap.put(printColumns.avgQuantitiesForMovieSession.name(), allQuantities / sessionsIds.size());
                printMap.put(printColumns.totalRevenueForMovie.name(), totalRevenueForMovie(sessions));
                System.out.println("movie: " + printMap);
                //movie: {totalRevenueForMovie=1450, allQuantitiesForMovie=8, avgQuantitiesForMovieSession=4, movie_name=Bad Boys for Life}
                //movie: {totalRevenueForMovie=1850, allQuantitiesForMovie=10, avgQuantitiesForMovieSession=5, movie_name=Sonic the Hedgehog}
                //movie: {totalRevenueForMovie=4500, allQuantitiesForMovie=11, avgQuantitiesForMovieSession=5, movie_name=Birds of Prey}
                //movie: {totalRevenueForMovie=3000, allQuantitiesForMovie=12, avgQuantitiesForMovieSession=6, movie_name=Dolittle}
                //movie: {totalRevenueForMovie=9250, allQuantitiesForMovie=24, avgQuantitiesForMovieSession=12, movie_name=The Invisible Man}

            });

        }
    }

    private Set<Integer> sessionsIds(List<HashMap<String, Object>> sessions) {
        Set<Integer> sessionsIds = new TreeSet<>();
        for (HashMap<String, Object> s : sessions) {
            Integer o = (Integer) s.get(RequestManager.columnLabels.id.name());
            sessionsIds.add(o);
        }
        return sessionsIds;
    }

    private BigDecimal totalRevenueForMovie(List<HashMap<String, Object>> sessions) {
        BigDecimal totalRevenueForMovie = BigDecimal.ZERO;
        for (HashMap<String, Object> s : sessions) {
            BigDecimal price = (BigDecimal) s.get(RequestManager.columnLabels.ticket_cost.name());
            int quantity = (int) s.get(RequestManager.columnLabels.quantity.name());
            BigDecimal sessionCost = price.multiply(BigDecimal.valueOf(quantity));
            totalRevenueForMovie = totalRevenueForMovie.add(sessionCost);
        }
        return totalRevenueForMovie;
    }

}
