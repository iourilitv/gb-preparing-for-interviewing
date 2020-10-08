package ru.geekbrains.lesson1.hw.task1;

import lombok.Builder;

@Builder
public class Person {
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final int age;
    private final String gender;
    private final String country;
    private final String address;
    private final String phone;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
