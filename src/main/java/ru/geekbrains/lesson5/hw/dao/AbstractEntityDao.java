package ru.geekbrains.lesson5.hw.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.geekbrains.lesson5.hw.dao.interfaces.IEntityDao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityDao<T, Id extends Serializable> implements IEntityDao<T, Id> {
    private static SessionFactory sessionFactory;
    protected Session currentSession;
    protected Transaction currentTransaction;
    protected Class<T> tClass;

    public AbstractEntityDao(Class<T> cl) {
        this.tClass = cl;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
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
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
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
