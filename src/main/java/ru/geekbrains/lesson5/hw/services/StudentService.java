package ru.geekbrains.lesson5.hw.services;

import org.hibernate.Session;
import ru.geekbrains.lesson5.hw.dao.StudentDao;
import ru.geekbrains.lesson5.hw.entities.Student;

import java.util.List;

public class StudentService extends AbstractEntityService<Student, Integer> {

    public StudentService() {
        dao = new StudentDao();
    }

    public void persistAll(List<Student> entities) {
        entities.forEach(this::persist);
    }

    public void persistAllInOneSession(List<Student> entities) {
        Session session = dao.openCurrentSession();
        for (Student entity : entities) {
            session.beginTransaction();
            dao.persist(entity);
            session.getTransaction().commit();
        }
        dao.closeCurrentSession();
    }

}
