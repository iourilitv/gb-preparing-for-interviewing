package ru.geekbrains.lesson5.hw.dao.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IEntityDao<T, Id extends Serializable> {
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Id id);
    List<T> findAll();
    void deleteAll();
}
