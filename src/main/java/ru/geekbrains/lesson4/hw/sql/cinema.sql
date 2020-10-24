/**
Hw for lesson 4.
@Author Litvinenko Yuriy
1. Задача про кинотеатр.
У фильма, который идет в кинотеатре, есть
название,
длительность (пусть будет 60, 90 или 120 минут),
цена билета (в разное время и дни может быть разной),
время начала сеанса (один фильм может быть показан несколько раз в разное время и с разной ценой билета).
Есть информация о купленных билетах (номер билета, на какой сеанс).

Задания:
Составить грамотную нормализованную схему хранения этих данных в БД.
Внести в нее
4–5 фильмов,
расписание на один день и
несколько проданных билетов.
 */

DROP TABLE IF EXISTS `tickets`;
DROP TABLE IF EXISTS `sessions`;
DROP TABLE IF EXISTS `movies`;
DROP TABLE IF EXISTS `day_schedules`;

CREATE TABLE `day_schedules` (
	`date` DATE NOT NULL,
    PRIMARY KEY (`date`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `movies` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` CHAR(255) NOT NULL,
    `running_time` TIME NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `sessions` (
    `id` INT NOT NULL AUTO_INCREMENT,
	`date` DATE NOT NULL,
    `movie_id` INT NOT NULL,
	`start` TIME NOT NULL,
    `duration` TIME NOT NULL,
	`stop` TIME NOT NULL,
	`ticket_cost` DECIMAL(10) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `session_date` FOREIGN KEY (`date`) REFERENCES `day_schedules` (`date`),
    CONSTRAINT `session_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `tickets` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `session_id` INT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `ticket_session_id` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

INSERT INTO `day_schedules` VALUES ('2020-10-18'), ('2020-10-19'), ('2020-10-20');

INSERT INTO `movies` (`name`, `running_time`) VALUES 
('Bad Boys for Life', '02:04:00'), 
('Sonic the Hedgehog', '01:38:00'), 
('Birds of Prey', '01:49:00'), 
('Dolittle', '01:41:00'), 
('The Invisible Man', '02:04:00');

INSERT INTO `sessions` (`date`, `movie_id`, `start`, `duration`, `stop`, `ticket_cost`) VALUES 
('2020-10-18', 1, '09:00:00', '02:20:00', '11:20:00', 150), 
('2020-10-18', 2, '11:00:00', '02:00:00', '13:00:00', 200), 
('2020-10-18', 3, '14:00:00', '02:00:00', '16:00:00', 250), 
('2020-10-18', 4, '16:30:00', '02:00:00', '18:00:00', 250), 
('2020-10-18', 5, '19:00:00', '02:20:00', '21:20:00', 500),
('2020-10-19', 2, '09:00:00', '02:00:00', '11:00:00', 150), 
('2020-10-19', 1, '11:00:00', '02:20:00', '13:20:00', 200), 
('2020-10-19', 4, '14:00:00', '02:00:00', '16:00:00', 250), 
('2020-10-19', 5, '16:30:00', '02:20:00', '18:50:00', 250), 
('2020-10-19', 3, '18:30:00', '02:00:00', '20:30:00', 500)
;

INSERT INTO `tickets` (`session_id`, `quantity`) VALUES 
(1, 2), (3, 1), (2, 4), (1, 1), (5, 3), (4, 2), (5, 1), (2, 3), (5, 4), (5, 5), (3, 3), (4, 3), (4, 1),
(6, 2), (7, 2), (9, 4), (10, 2), (8, 3), (10, 2), (6, 1), (8, 3), (9, 4), (7, 2), (9, 3), (10, 3), (7, 1)
;

