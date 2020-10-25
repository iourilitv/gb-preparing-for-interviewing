package ru.geekbrains.lesson5.hw.services;

import ru.geekbrains.lesson5.hw.dao.AbstractEntityDao;
import ru.geekbrains.lesson5.hw.services.interfaces.IEntityService;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractEntityService<T, Id extends Serializable> implements IEntityService<T, Id> {
    protected AbstractEntityDao<T, Id> dao;

    @Override
    public void persist(T entity) {
        dao.openCurrentSessionWithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(T entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(T entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public T findById(Id id) {
        dao.openCurrentSession();
        T entity = dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    @Override
    public List<T> findAll() {
        dao.openCurrentSession();
        List<T> entities = dao.findAll();
        dao.closeCurrentSession();
        return entities;
    }

    @Override
    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

}
