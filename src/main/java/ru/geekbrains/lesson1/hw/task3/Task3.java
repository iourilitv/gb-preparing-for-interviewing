package ru.geekbrains.lesson1.hw.task3;

import ru.geekbrains.lesson1.hw.task1.classbook.Task1ClassBook;

import java.util.logging.Logger;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 3. Написать пример кода, который реализует принцип полиморфизма, на примере фигур — круг,
 * квадрат, треугольник.
 */
public class Task3 {
    private static final Logger log = Logger.getLogger(Task1ClassBook.class.getName());

    public static void main(String[] args) {
        Circle circle = new Circle(8.0);
        Square square = new Square(10.0);
        Triangle triangle = new Triangle(17.0, 12.0);

        log.info("Areas of circle= " + circle.area() + ", square= " + square.area() +
                ", triangle= " + triangle.area());
    }
}
