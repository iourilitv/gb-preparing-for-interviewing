package ru.geekbrains.lesson4.hw;

import java.util.*;

public class PrintService {
    enum printSchemas {
        schema1, schema2, schema3, schema4
    }
    enum printColumns {
        movie_name, allQuantities, avgQuantitiesForSession, schema4
    }

    private final RequestManager requestManager;
    private final Map<String, Object> printMap = new HashMap<>();
    private final List<Integer> ids;

    public PrintService() {
        this.requestManager =  new RequestManager();
        this.ids = requestManager.getMoviesIdList();
        System.out.println(ids);
    }

    public void print(PrintService.printSchemas printSchema) {

        if(printSchema.equals(printSchemas.schema3)) {
            ids.forEach(i -> {
                List<HashMap<String, Object>> sessions = requestManager.getSessionsJoinMoviesAndTicketsByMovieId(i);

                Set<Integer> sessionsIds = new TreeSet<>();
                for (HashMap<String, Object> s : sessions) {
                    Integer o = (Integer) s.get(RequestManager.columnLabels.id.name());
                    sessionsIds.add(o);
                }
                int allQuantities = sessions.stream().mapToInt(s -> (int) s.get(RequestManager.columnLabels.quantity.name())).sum();

                printMap.put(printColumns.movie_name.name(), sessions.get(0).get(RequestManager.columnLabels.movie_name.name()));
                printMap.put(printColumns.allQuantities.name(), allQuantities);
                printMap.put(printColumns.avgQuantitiesForSession.name(), allQuantities / sessionsIds.size());
                System.out.println("movie: " + printMap);

            });

        }
    }



}
