package ru.geekbrains.lesson5.hw.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
    }

//    private Session currentSession;
//    private Transaction currentTransaction;
//
//    public Session openCurrentSession() {
//        currentSession = HibernateUtil.getSessionFactory().openSession();
//        return currentSession;
//    }
//
//    public Session openCurrentSessionWithTransaction() {
//        currentSession = HibernateUtil.getSessionFactory().openSession();
//        currentTransaction = currentSession.beginTransaction();
//        return currentSession;
//    }
//
//    public void closeCurrentSession() {
//        currentSession.close();
//    }
//
//    public void closeCurrentSessionWithTransaction() {
//        currentTransaction.commit();
//        currentSession.close();
//    }

}
