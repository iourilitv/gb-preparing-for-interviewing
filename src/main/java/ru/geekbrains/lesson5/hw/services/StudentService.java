package ru.geekbrains.lesson5.hw.services;

import org.hibernate.Session;
import ru.geekbrains.lesson5.hw.dao.StudentDao;
import ru.geekbrains.lesson5.hw.entities.Student;
import ru.geekbrains.lesson5.hw.services.interfaces.IEntityService;

import java.util.List;

public class StudentService implements IEntityService<Student, Integer> {
    private final StudentDao dao;

    public StudentService() {
        dao = new StudentDao();
    }

    @Override
    public void persist(Student entity) {
        dao.openCurrentSessionWithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionWithTransaction();
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

    @Override
    public void update(Student entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Student entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public Student findById(Integer id) {
        dao.openCurrentSession();
        Student student = dao.findById(id);
        dao.closeCurrentSession();
        return student;
    }

    @Override
    public List<Student> findAll() {
        dao.openCurrentSession();
        List<Student> students = dao.findAll();
        dao.closeCurrentSession();
        return students;
    }

    @Override
    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

}
