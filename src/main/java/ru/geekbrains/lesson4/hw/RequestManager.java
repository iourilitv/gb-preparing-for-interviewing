package ru.geekbrains.lesson4.hw;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestManager {
    private final Connection connection = new MySQLConnect().connect();

    public List<HashMap<String, Object>> getSchema1() {
        String sql = "select s.date, m.name as movie1, s.start, s.duration, s.stop, tab1.name as movie2, tab1.start, tab1.duration, tab1.stop from sessions as s \n" +
                "\tjoin movies as m on s.movie_id = m.id \n" +
                "    join (select s.date, m.name, s.start, s.duration, s.stop from sessions as s \n" +
                "\t\tjoin movies as m on s.movie_id = m.id \n" +
                "\t\torder by date, start) as tab1\n" +
                "        where s.date = tab1.date and tab1.start < s.stop and s.start < tab1.start\n" +
                "        order by s.date, s.start;";
        return makeRequest(sql);
    }
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

    public List<HashMap<String, Object>> getSchema4() {
        String sql = "select (case when s.start between '09:00:00' and '14:59:59' then '09:00:00-14:59:59' else \n" +
                "\t\t\t(case when s.start between '15:00:00' and '17:59:59' then '15:00:00-17:59:59' else \n" +
                "\t\t\t\t (case when s.start between '18:00:00' and '20:59:59' then '18:00:00-20:59:59' else \n" +
                "                 \t\t\t\t (case when s.start between '21:00:00' and '23:59:59' then '21:00:00-23:59:59' else 0 end)\n" +
                "                 end)\n" +
                "\t\t\tend)\n" +
                "\t\tend) as period, \n" +
                "\tsum(quantity) as qua_sum, sum(ticket_cost * quantity) as tickets_cost_sum from sessions as s\n" +
                "\tjoin tickets as t on t.session_id = s.id\n" +
                "    group by period;";
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
