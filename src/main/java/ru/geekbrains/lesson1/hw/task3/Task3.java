package ru.geekbrains.lesson1.hw.task3;

import ru.geekbrains.lesson1.hw.task1lombok.Genders;
import ru.geekbrains.lesson1.hw.task1lombok.Person;
import ru.geekbrains.lesson1.hw.task1lombok.PersonBuilder;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 3. Написать пример кода, который реализует принцип полиморфизма, на примере фигур — круг,
 * квадрат, треугольник.
 */
public class Task3 {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.createNewPerson("Yuriy", "Litvinenko", "Semenovich", 55,
                Genders.Male.name(), "Russia", "Senina, 44", "+7 999 999-99-99");
    }
}
