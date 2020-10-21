package ru.geekbrains.lesson4.hw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintService {
    enum printSchemas {
        schema1, schema2, schema3, schema4
    }

    private final RequestManager requestManager;
//    private final List<HashMap<String, Object>> allSessions;

    public PrintService() {
        this.requestManager =  new RequestManager();
//        this.allSessions = requestManager.getAllSessionsJoinMoviesAndTickets();
    }

    public void print(PrintService.printSchemas printSchema) {
        Map<String, Object> printMap = new HashMap<>();
        if(printSchema.equals(printSchemas.schema3)) {
            List<Integer> ids = requestManager.getMoviesIdList();
            System.out.println(ids);
            ids.forEach(i -> {
                List<HashMap<String, Object>> sessions = requestManager.getSessionsJoinMoviesAndTicketsByMovieId(i);
                System.out.println(sessions);
                int allQuantities = sessions.stream().mapToInt(s -> (int) s.get(RequestManager.columnLabels.quantity.name())).sum();
                System.out.println(allQuantities);
                printMap.put(RequestManager.columnLabels.movie_name.name(), sessions.get(0).get(RequestManager.columnLabels.movie_name.name()));
                printMap.put("allQuantities", allQuantities);
                System.out.println(printMap);
            });
        }
    }

}
