package ru.geekbrains.lesson5.hw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.geekbrains.lesson5.hw.dao.interfaces.IEntityDao;
import ru.geekbrains.lesson5.hw.entities.Mark;
import ru.geekbrains.lesson5.hw.persistence.HibernateUtil;

import java.util.List;

public class MarkDao implements IEntityDao<Mark, Short> {
    private Session currentSession;
    private Transaction currentTransaction;

    @Override
    public void persist(Mark mark) {
        currentSession.save(mark);
    }

    @Override
    public void update(Mark mark) {
        currentSession.update(mark);
    }

    @Override
    public void delete(Mark mark) {
        currentSession.delete(mark);
    }

    @Override
    public Mark findById(Short id) {
        return currentSession.get(Mark.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mark> findAll() {
        return (List<Mark>) currentSession.createQuery("from Mark").list();
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
