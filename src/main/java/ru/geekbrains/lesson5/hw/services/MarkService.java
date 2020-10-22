package ru.geekbrains.lesson5.hw.services;

import ru.geekbrains.lesson5.hw.dao.MarkDao;
import ru.geekbrains.lesson5.hw.entities.Mark;
import ru.geekbrains.lesson5.hw.services.interfaces.IEntityService;

import java.util.List;

public class MarkService implements IEntityService<Mark, Short> {
    private final MarkDao dao;

    public MarkService() {
        dao = new MarkDao();
    }

    @Override
    public void persist(Mark entity) {
        dao.openCurrentSessionWithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Mark entity) {
        dao.openCurrentSessionWithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void delete(Mark entity) {
        dao.openCurrentSessionWithTransaction();
        dao.delete(entity);
        dao.closeCurrentSessionWithTransaction();
    }

    @Override
    public Mark findById(Short id) {
        dao.openCurrentSession();
        Mark mark = dao.findById(id);
        dao.closeCurrentSession();
        return mark;
    }

    @Override
    public List<Mark> findAll() {
        dao.openCurrentSession();
        List<Mark> marks = dao.findAll();
        dao.closeCurrentSession();
        return marks;
    }

    @Override
    public void deleteAll() {
        dao.openCurrentSessionWithTransaction();
        dao.deleteAll();
        dao.closeCurrentSessionWithTransaction();
    }

}
