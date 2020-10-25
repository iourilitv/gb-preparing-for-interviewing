package ru.geekbrains.lesson5.hw.services;

import ru.geekbrains.lesson5.hw.dao.MarkDao;
import ru.geekbrains.lesson5.hw.entities.Mark;

public class MarkService extends AbstractEntityService<Mark, Short> {

    public MarkService() {
        dao = new MarkDao();
    }

}
