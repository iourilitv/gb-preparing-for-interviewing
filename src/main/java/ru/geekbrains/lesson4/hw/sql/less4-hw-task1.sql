/**Сделать запросы, считающие и выводящие в понятном виде:
1) ошибки в расписании (фильмы накладываются друг на друга), 
отсортированные по возрастанию времени. 
Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
*/

select s.date, m.name as movie1, s.start, s.duration, s.stop, tab1.name as movie2, tab1.start, tab1.duration, tab1.stop from sessions as s 
	join movies as m on s.movie_id = m.id 
    join (select s.date, m.name, s.start, s.duration, s.stop from sessions as s 
		join movies as m on s.movie_id = m.id 
		order by date, start) as tab1
        where s.date = tab1.date and tab1.start < s.stop and s.start < tab1.start
        order by s.date, s.start;
/**
date, 				movie1, 		 start, 	duration, 	  stop, 		movie2, 			start, 	duration, 		stop
'2020-10-18', 'Bad Boys for Life', '09:00:00', '02:20:00', '11:20:00', 'Sonic the Hedgehog', '11:00:00', '02:00:00', '13:00:00'
'2020-10-19', 'The Invisible Man', '16:30:00', '02:20:00', '18:50:00', 'Birds of Prey', 	 '18:30:00', '02:00:00', '20:30:00'
*/

# Не понял как выводить первую запись, вместо последней, поэтому просто поменял колонки фильмов местами.