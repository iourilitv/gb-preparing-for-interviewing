package ru.geekbrains.lesson4.hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestManager {
    private final Connection connection = new MySQLConnect().connect();
    enum columnLabels {
        id, date, movie_id, start, duration, stop, ticket_cost, movie_name, ticket_id, quantity
    }

    public List<Integer> getMoviesIdList() {
        String sql = "select id from movies;";
        List<Integer> ids = new ArrayList<>();
        makeRequest(sql).forEach(m -> ids.add((int)m.get("id")));
        return ids;
    }

    public List<HashMap<String, Object>> getSessionsJoinMoviesAndTicketsByMovieId(int movieId) {
        String sql = String.format("select s.*, m.name as " + columnLabels.movie_name.name() +
                ", t.id as " + columnLabels.ticket_id.name() +
                ", t.quantity as " + columnLabels.quantity.name() +
                " from sessions as s join movies as m on s.movie_id = m.id join tickets as t on t.session_id = s.id" +
                " where s.movie_id = '%d'", movieId);
        return makeRequest(sql);
    }

    private List<HashMap<String, Object>> makeRequest(String sql) {
        List<HashMap<String, Object>> rows = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>(columns);
                for(int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnLabel(i), rs.getObject(i));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
