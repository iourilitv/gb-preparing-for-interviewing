package ru.geekbrains.lesson3.hw.task1;

public class PingPongQueue {
    private boolean flag;
    private int counter = 10;

    synchronized public void printPing(String context) throws InterruptedException {
        while(flag) {
            wait();
        }
        System.out.println(counter-- + ": " + context);
        flag = true;
        Thread.sleep(1000);
        notify();
    }

    synchronized public void printPong(String context) throws InterruptedException {
        while(!flag) {
            wait();
        }
        System.out.println(counter-- + ": " + context);
        flag = false;
        Thread.sleep(1000);
        notify();
    }

    public int getCounter() {
        return counter;
    }
}
