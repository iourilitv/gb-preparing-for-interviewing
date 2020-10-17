package ru.geekbrains.lesson3.hw.task2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ConcurrencyLock implements Runnable {
    private final MultiThreadsCounter multiThreadsCounter;
    private final Lock lock;

    public ConcurrencyLock(MultiThreadsCounter r, String name){
        this.multiThreadsCounter = r;
        this.lock = r.getLock();
        new Thread(this, name).start();
    }

    //Вариант1. Блокирование в вызываемом методе
    @Override
    public void run() {
        while(multiThreadsCounter.getCount() < 100) {
            multiThreadsCounter.incrementLocked(Thread.currentThread().getName());
        }
    }

    //Вариант2. Блокирование в вызывающем потоке
//    @Override
//    public void run() {
//        while(multiThreadsCounter.getCount() < 100) {
//            try {
//                //пытаемся взять лок в течение N секунд
//                if(lock.tryLock(1, TimeUnit.SECONDS)){
//                    multiThreadsCounter.increment(Thread.currentThread().getName());
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally{
//                //убираем лок
//                lock.unlock();
//            }
//
//        }
//    }
}
