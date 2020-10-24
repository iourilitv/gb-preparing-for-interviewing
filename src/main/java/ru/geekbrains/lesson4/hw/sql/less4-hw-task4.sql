/** Сделать запросы, считающие и выводящие в понятном виде:
4) число посетителей и кассовые сборы, сгруппированные по времени начала фильма: 
с 9 до 15, 
с 15 до 18, 
с 18 до 21, 
с 21 до 00:00 
(сколько посетителей пришло с 9 до 15 часов и т.д.).
 */

select (case when s.start between '09:00:00' and '14:59:59' then '09:00:00-14:59:59' else 
			(case when s.start between '15:00:00' and '17:59:59' then '15:00:00-17:59:59' else 
				 (case when s.start between '18:00:00' and '20:59:59' then '18:00:00-20:59:59' else 
                 				 (case when s.start between '21:00:00' and '23:59:59' then '21:00:00-23:59:59' else 0 end)
                 end)
			end)
		end) as period, 
	sum(quantity) as qua_sum, sum(ticket_cost * quantity) as tickets_cost_sum from sessions as s
	join tickets as t on t.session_id = s.id
    group by period;
/**
period, 			qua_sum, tickets_cost_sum
'09:00:00-14:59:59', '28', 		'5800'
'15:00:00-17:59:59', '17', 		'4250'
'18:00:00-20:59:59', '20', 		'10000'
*/
