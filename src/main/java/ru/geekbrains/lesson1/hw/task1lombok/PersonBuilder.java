package ru.geekbrains.lesson1.hw.task1lombok;

import java.util.logging.Logger;

public class PersonBuilder {
    Logger log = Logger.getLogger(this.getClass().getName());

    public Person createNewPerson(String firstName, String lastName, String middleName, int age, String gender,
                                  String country, String address, String phone) {
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .age(age)
                .gender(gender)
                .country(country)
                .address(address)
                .phone(phone)
                .build();
        log.info("*** Created a new Person: " + person);
        return person;
    }
}
