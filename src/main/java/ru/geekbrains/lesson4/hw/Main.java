package ru.geekbrains.lesson4.hw;

/**
 *  Hw for lesson 4.
 *  @Author Litvinenko Yuriy
 * Сделать запросы, считающие и выводящие в понятном виде:
 3) список фильмов, для каждого — с указанием
 - общего числа посетителей за все время,
 - среднего числа зрителей за сеанс и
 - общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
 - Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 */
public class Main {
    public static void main(String[] args) {
        new PrintService().print(PrintService.printSchemas.schema3);
    }
}
//        List<List<HashMap<String, Object>>> sessions = new ArrayList<>();
//        List<HashMap<String, Object>> movies =  manager.getMovies();
//        System.out.println(movies);
//        //[{name=Bad Boys for Life, running_time=05:04:00, id=1}, {name=Sonic the Hedgehog, running_time=04:38:00, id=2}, {name=Birds of Prey, running_time=04:49:00, id=3}, {name=Dolittle, running_time=04:41:00, id=4}, {name=The Invisible Man, running_time=05:04:00, id=5}]
//        movies.forEach(m -> sessions.add(manager.getSessionsByMovieId((int)m.get("id"))));
//        System.out.println(sessions);
//        //[[{movie=Bad Boys for Life, session_=1}, {movie=Bad Boys for Life, session_=7}], [{movie=Sonic the Hedgehog, session_=2}, {movie=Sonic the Hedgehog, session_=6}], [{movie=Birds of Prey, session_=3}, {movie=Birds of Prey, session_=10}], [{movie=Dolittle, session_=4}, {movie=Dolittle, session_=8}], [{movie=The Invisible Man, session_=5}, {movie=The Invisible Man, session_=9}]]