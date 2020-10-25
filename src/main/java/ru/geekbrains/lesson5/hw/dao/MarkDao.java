package ru.geekbrains.lesson5.hw.dao;

import ru.geekbrains.lesson5.hw.entities.Mark;

import java.util.List;

public class MarkDao extends AbstractEntityDao<Mark, Short> {

    @Override
    public Mark findById(Short id) {
        return currentSession.get(Mark.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Mark> findAll() {
        return (List<Mark>) currentSession.createQuery("from Mark").list();
    }

}
