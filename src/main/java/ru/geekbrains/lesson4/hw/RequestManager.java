package ru.geekbrains.lesson4.hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestManager {
    private final Connection connection = new MySQLConnect().connect();

    public List<HashMap<String, Object>> getSchema3() {
        String sql = "select * from \n" +
                "(select tab1.movie, sum(tab1.sess_qua_sum) as movie_qua_sum, avg(tab1.sess_qua_sum) as movie_sess_qua_avg, sum(tab1.ses_cost) as movie_cost_sum\n" +
                "\tfrom\n" +
                "\t\t(select s.id as session_id, m.name as movie, sum(t.quantity) as sess_qua_sum, sum(t.quantity * s.ticket_cost) as ses_cost from sessions as s \n" +
                "\t\t\tjoin movies as m on s.movie_id = m.id\n" +
                "\t\t\tjoin tickets as t on t.session_id = s.id\n" +
                "\t\t\tgroup by s.id) as tab1\n" +
                "\t\tgroup by movie\n" +
                "\t\torder by movie_cost_sum desc) as tab2\n" +
                "union all\n" +
                "select 'Total' as movie, sum(movie_qua_sum) as total_movie_qua, '' as movie_sess_qua_avg, sum(movie_cost_sum) as total_movie_cost \n" +
                "\tfrom \n" +
                "\t\t(select tab1.movie, sum(tab1.sess_qua_sum) as movie_qua_sum, avg(tab1.sess_qua_sum) as movie_sess_qua_avg, sum(tab1.ses_cost) as movie_cost_sum\n" +
                "\t\t\tfrom\n" +
                "\t\t\t\t(select s.id as session_id, m.name as movie, sum(t.quantity) as sess_qua_sum, sum(t.quantity * s.ticket_cost) as ses_cost from sessions as s \n" +
                "\t\t\t\t\tjoin movies as m on s.movie_id = m.id\n" +
                "\t\t\t\t\tjoin tickets as t on t.session_id = s.id\n" +
                "\t\t\t\t\tgroup by s.id) as tab1\n" +
                "\t\t\t\tgroup by movie\n" +
                "\t\t\t\torder by movie_cost_sum desc) as tab3;";
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
