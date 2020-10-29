package ru.geekbrains.lesson4.hw;

/**
 *  Hw for lesson 4.
 *  @Author Litvinenko Yuriy
 * Сделать запросы, считающие и выводящие в понятном виде:
 * DONE 1) ошибки в расписании (фильмы накладываются друг на друга),
 * отсортированные по возрастанию времени.
 * Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
 * DONE 3) список фильмов, для каждого — с указанием
 *  - название,
 *  - общего числа посетителей за все время,
 *  - среднего числа зрителей за сеанс и
 *  - общей суммы сборов по каждому фильму
 *  - отсортировать по убыванию прибыли.
 *  - Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 * DONE 4) число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
 * с 9 до 15,
 * с 15 до 18,
 * с 18 до 21,
 * с 21 до 00:00
 * (сколько посетителей пришло с 9 до 15 часов и т.д.).
 */
public class Main {
    public static void main(String[] args) {
        new PrintService().print(PrintService.printSchemas.schema4);
    }
}