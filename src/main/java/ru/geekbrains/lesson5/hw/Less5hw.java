package ru.geekbrains.lesson5.hw;

import ru.geekbrains.lesson5.hw.entities.Mark;
import ru.geekbrains.lesson5.hw.entities.Student;
import ru.geekbrains.lesson5.hw.services.MarkService;
import ru.geekbrains.lesson5.hw.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        StudentService studentService = new StudentService();

        MarkService markService = new MarkService();
        Mark mark = markService.findById((short) 1);

        System.out.println(mark);
        //Mark{id=1, title='A'}

        Student student = Student.builder()
                                    .firstName("Yuriy")
                                    .lastName("Litvinenko")
                                    .mark(mark)
                                    .build();

        studentService.persist(student);
        System.out.println("after persist(student). student: " + studentService.findById(student.getId()));
        System.out.println("after persist(student). all students: " + studentService.findAll());
        //after persist(student). student: Student{id=9206, firstName='Yuriy', lastName='Litvinenko', mark=Mark{id=1, title='A'}}
        //after persist(student). all students: [Student{id=9206, firstName='Yuriy', lastName='Litvinenko', mark=Mark{id=1, title='A'}}]

        Mark mark2 = markService.findById((short) 3);
        student.setMark(mark2);
        studentService.update(student);
        System.out.println("after update(student). student: " + studentService.findById(student.getId()));
        System.out.println("after update(student). all students: " + studentService.findAll());
        //after update(student). student: Student{id=9206, firstName='Yuriy', lastName='Litvinenko', mark=Mark{id=3, title='C'}}
        //after update(student). all students: [Student{id=9206, firstName='Yuriy', lastName='Litvinenko', mark=Mark{id=3, title='C'}}]

        studentService.delete(student);
        System.out.println("after delete(student). student: " + studentService.findById(student.getId()));
        System.out.println("after delete(student). all students: " + studentService.findAll());
        //after delete(student). student: null
        //after delete(student). all students: []

//        speedTests(studentService);

        System.out.println("before deleteAll(). all students: " + studentService.findAll());
        studentService.deleteAll();
        System.out.println("after deleteAll(). all students: " + studentService.findAll());

    }

    private static void speedTests(StudentService studentService) {
//****** Тестирование скорости записи. НАЧАЛО ********
        //Test1. В одном цикле создаем и сохраняем каждый объект
//          createAndPersistStudents(1000, studentService);
        //test.createAndPersistStudents() lasted(mc): 105073

        List<Student> students = createStudentsList(1000);
        //test.createStudentsList() lasted(mc): 8375

        //Test2. Тоже что и Test1 - но сохраняем готовые объекты из списка
//        persistStudentsListByEveryone(students, studentService);
        //test.persistStudentsListByEveryone() lasted(mc): 74417 + 8375 = 82792

        //Test3. Сохраняем объекты из списка в одной сессии, но в отдельной транзакции
//        persistStudentsListInOneSession(students, studentService);
        //test.persistStudentsListInOneSession() lasted(mc): 87755 + 8375 = 96130

        //Test4. Сохраняем каждый объект из списка в новой сессии с транзакцией
        addStudentsByPersistAll(students, studentService);
        //test.addStudentsByPersistAll() lasted(mc): 103104 + 8375 = 111479

//       ВЫВОД:
//       Быстрее сначала подготовить коллекцию, а затем сохранить каждый элемент в цикле(Test2).
//       Test2 быстрее, чем Test1, видимо, из-за торможения цикла на операции сохранения в бд в цикле Test1.
//****** Тестирование скорости записи. КОНЕЦ ********
    }

    private static List<Student> createStudentsList(int quantity) {
        long start = System.currentTimeMillis();
        MarkService markService = new MarkService();
        List<Mark> marks = markService.findAll();
        Random random = new Random();
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= quantity; i++) {
            Student student = Student.builder()
                    .firstName("Student" + i + " first name")
                    .lastName("Student" + i + " last name")
                    .mark(marks.get(random.nextInt(marks.size())))
                    .build();
            students.add(student);
        }
        long finish = System.currentTimeMillis();
        System.out.println("test.createStudentsList() lasted(mc): " + (finish - start));
        //
        return students;
    }

    private static void createAndPersistStudents(int quantity, StudentService studentService) {
        long start = System.currentTimeMillis();
        MarkService markService = new MarkService();
        List<Mark> marks = markService.findAll();
        Random random = new Random();
        for (int i = 1; i <= quantity; i++) {
            Student student = Student.builder()
                    .firstName("Student" + i + " first name")
                    .lastName("Student" + i + " last name")
                    .mark(marks.get(random.nextInt(marks.size())))
                    .build();
            studentService.persist(student);
        }
        long finish = System.currentTimeMillis();
        System.out.println("test.createAndPersistStudents() lasted(mc): " + (finish - start));
    }

    private static void persistStudentsListByEveryone(List<Student> students, StudentService studentService) {
        long start = System.currentTimeMillis();
        students.forEach(studentService::persist);
        long finishFinal = System.currentTimeMillis();
        System.out.println("test.persistStudentsListByEveryone() lasted(mc): " + (finishFinal - start));
    }

    private static void persistStudentsListInOneSession(List<Student> students, StudentService studentService) {
        long start = System.currentTimeMillis();
        studentService.persistAllInOneSession(students);
        long finishFinal = System.currentTimeMillis();
        System.out.println("test.persistStudentsListInOneSession() lasted(mc): " + (finishFinal - start));
    }

    private static void addStudentsByPersistAll(List<Student> students, StudentService studentService) {
        long start = System.currentTimeMillis();
        studentService.persistAll(students);
        long finishFinal = System.currentTimeMillis();
        System.out.println("test.addStudentsByPersistAll() lasted(mc): " + (finishFinal - start));
    }

}
