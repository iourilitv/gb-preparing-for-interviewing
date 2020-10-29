package ru.geekbrains.lesson5.hw.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.geekbrains.lesson5.hw.dao.interfaces.IEntityDao;
import ru.geekbrains.lesson5.hw.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityDao<T, Id extends Serializable> implements IEntityDao<T, Id> {
    protected Session currentSession;
    protected Transaction currentTransaction;
    protected Class<T> tClass;

    public AbstractEntityDao(Class<T> cl) {
        this.tClass = cl;
    }

    @Override
    public void persist(T entity) {
        currentSession.save(entity);
    }

    @Override
    public void update(T entity) {
        currentSession.update(entity);
    }

    @Override
    public void delete(T entity) {
        currentSession.delete(entity);
    }

    @Override
    public T findById(Id id) {
        return currentSession.get(tClass, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return (List<T>) currentSession.createQuery("from " + tClass.getSimpleName()).list();
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
