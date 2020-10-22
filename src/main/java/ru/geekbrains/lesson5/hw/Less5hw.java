package ru.geekbrains.lesson5.hw;

import ru.geekbrains.lesson5.hw.entities.Mark;
import ru.geekbrains.lesson5.hw.entities.Student;
import ru.geekbrains.lesson5.hw.services.MarkService;
import ru.geekbrains.lesson5.hw.services.StudentService;

/**
 * Hw for lesson 5.
 * @Author Litvinenko Yuriy
 *
 * 1. Создать базу данных Student с полями id, name, mark.
 * 2. Создать сущность Student и добавить в нее аннотации. Поле id должно заполняться автоматически.
 * 3. Создать конфигурационный файл hibernate.cfg.xml, в котором указать свойства для подключения к БД и список классов с аннотациями Entity.
 * 4. Создать класс со статическим методом, который возвращает объект SessionFactory.
 * 5. Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи по ID и всех записей.
 * 6. Добавить 1000 записей в таблицу Student.
 * 7. Проверить работоспособность приложения, выполнить методы по удалению, изменению, добавлению записей, а также выборки одной и всех записей.
 */
public class Less5hw {
    public static void main(String[] args) {
        MarkService markService = new MarkService();
        Mark mark = markService.findById((short) 1);

        System.out.println(mark);

        StudentService studentService = new StudentService();
        Student student = Student.builder()
                                    .firstName("Yuriy")
                                    .lastName("Litvinenko")
                                    .mark(mark)
                                    .build();

//        studentService.persist(student);
//        System.out.println("after persisting student: " + student);
//        studentService.delete(student);
//        System.out.println("after deleting student: " + student);

        System.out.println("after deleting. all students: " + studentService.findAll());

    }

}
