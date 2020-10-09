package ru.geekbrains.lesson1.hw.task1.lombok;

/**
 * Hw for lesson 1.
 * @Author Litvinenko Yuriy
 * 1. Создать builder для класса Person со следующими полями:
 *    String firstName, String lastName, String middleName, String country, String address, String phone,
 *    int age, String gender.
 */
public class Task1Lombok {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.createNewPerson("Yuriy", "Litvinenko", "Semenovich", 55,
                Genders.Male.name(), "Russia", "Senina, 44", "+7 999 999-99-99");
    }
}
