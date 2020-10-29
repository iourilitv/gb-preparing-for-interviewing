package ru.geekbrains.lesson5.hw.dao;

import ru.geekbrains.lesson5.hw.entities.Mark;

public class MarkDao extends AbstractEntityDao<Mark, Short> {

    public MarkDao() {
        super(Mark.class);
    }

}
