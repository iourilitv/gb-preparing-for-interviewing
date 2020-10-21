package ru.geekbrains.lesson4.hw;

/**
 *  Hw for lesson 4.
 *  @Author Litvinenko Yuriy
 * Сделать запросы, считающие и выводящие в понятном виде:
 3) список фильмов, для каждого — с указанием
 - DONE название,
 - DONE общего числа посетителей за все время,
 - DONE среднего числа зрителей за сеанс и
 - DONE общей суммы сборов по каждому фильму
 - отсортировать по убыванию прибыли.
 - Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 */
public class Main {
    public static void main(String[] args) {
        new PrintService().print(PrintService.printSchemas.schema3);
    }
}