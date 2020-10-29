package ru.geekbrains.lesson5.hw.dao;

import ru.geekbrains.lesson5.hw.entities.Student;

public class StudentDao extends AbstractEntityDao<Student, Integer> {

    public StudentDao() {
        super(Student.class);
    }

}
