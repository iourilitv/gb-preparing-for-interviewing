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

    //TODO How to replace Student.class with T.class? T.class does not work!
    public abstract T findById(Id id);
//    @Override
//    public Student findById(Integer id) {
//        return currentSession.get(Student.class, id);
//    }

    //TODO How to replace Student.class with T.class? T.class does not work!
    public abstract List<T> findAll();
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<Student> findAll() {
//        return (List<Student>) currentSession.createQuery("from Student").list();
//    }

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
