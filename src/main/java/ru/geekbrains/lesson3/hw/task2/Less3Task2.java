package ru.geekbrains.lesson3.hw.task2;

/**
 * Hw for lesson 3.
 * @Author Litvinenko Yuriy
 * 2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
 * Source: Java Lock API. Теория и пример использования https://javadevblog.com/java-lock-teoriya-i-primer-ispol-zovaniya-concurrency-lock.html
 * Source: Многопоточное программирование в Java 8. Часть вторая. Синхронизация доступа к изменяемым объектам https://tproger.ru/translations/java8-concurrency-tutorial-2/
 */
public class Less3Task2 {

    public static void main(String[] args) {
        MultiThreadsCounter multiThreadsCounter = new MultiThreadsCounter();
        new ConcurrencyLock(multiThreadsCounter, "Thread-1");
        new ConcurrencyLock(multiThreadsCounter, "Thread-2");

    }




}
