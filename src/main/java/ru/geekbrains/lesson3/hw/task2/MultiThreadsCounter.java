package ru.geekbrains.lesson3.hw.task2;

import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadsCounter {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    //Вариант1. Блокирование в вызываемом методе
    void incrementLocked(String name) {
        lock.lock();
        try {
            System.out.println("Thread name: " + name  + ", count= " + count++);
        } finally {
            lock.unlock();
        }
    }

    //Вариант2. Блокирование в вызывающем потоке
    void increment(String name) {
        System.out.println("Thread name: " + name  + ", count= " + count++);
    }

    public int getCount() {
        return count;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
