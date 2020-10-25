package ru.geekbrains.lesson5.hw.dao;

import ru.geekbrains.lesson5.hw.entities.Student;

import java.util.List;

public class StudentDao extends AbstractEntityDao<Student, Integer> {

    public Student findById(Integer id) {
        return currentSession.get(Student.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        return (List<Student>) currentSession.createQuery("from Student").list();
    }
}
