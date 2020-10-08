package ru.geekbrains.lesson1.hw.task1;

import ru.geekbrains.lesson1.hw.task1lombok.Genders;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 1. Создать builder для класса Person со следующими полями:
 *    String firstName, String lastName, String middleName, String country, String address, String phone,
 *    int age, String gender.
 */
public class Task1 {
    public static void main(String[] args) {
        Person person = new Person.Builder()
                .firstName("Yuriy")
                .lastName("Litvinenko")
                .middleName("Semenovich")
                .age(55)
                .gender(Genders.Male.name())
                .country("Russia")
                .address("Senina, 44")
                .phone("+7 999 999-99-99")
                .build();
    }
}
