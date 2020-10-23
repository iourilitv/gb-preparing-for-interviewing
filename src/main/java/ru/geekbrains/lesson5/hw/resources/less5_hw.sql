DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `marks`;

CREATE TABLE `marks` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` CHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `students` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` CHAR(255) NOT NULL,
	`last_name` CHAR(255) NOT NULL,
	`mark_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `students_names_idx` (`first_name`,`last_name`),
    CONSTRAINT `students_mark_id` FOREIGN KEY (`mark_id`) REFERENCES `marks` (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

INSERT INTO `marks`(`title`) VALUES ('A'), ('B'), ('C'), ('D'), ('E');