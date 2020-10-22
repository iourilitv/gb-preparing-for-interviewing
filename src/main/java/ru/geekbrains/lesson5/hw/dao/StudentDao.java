package ru.geekbrains.lesson5.hw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.geekbrains.lesson5.hw.dao.interfaces.IEntityDao;
import ru.geekbrains.lesson5.hw.entities.Student;
import ru.geekbrains.lesson5.hw.persistence.HibernateUtil;

import java.util.List;

public class StudentDao implements IEntityDao<Student, Integer> {
    protected Session currentSession;
    protected Transaction currentTransaction;

    public StudentDao() {

    }

    @Override
    public void persist(Student student) {
        currentSession.save(student);
    }

    @Override
    public void update(Student student) {
        currentSession.update(student);
    }

    @Override
    public void delete(Student student) {
        currentSession.delete(student);
    }

    @Override
    public Student findById(Integer id) {
        return currentSession.get(Student.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        return (List<Student>) currentSession.createQuery("from Student").list();
    }

    @Override
    public void deleteAll() {
        findAll().forEach(this::delete);
    }

    public Session openCurrentSession() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = HibernateUtil.getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
}
