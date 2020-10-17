package ru.geekbrains.lesson3.hw.task1;

import lombok.SneakyThrows;

public class Pong implements Runnable {
    private final PingPongQueue queue;

    public Pong(PingPongQueue queue) {
        this.queue = queue;
        new Thread(this, "Pong").start();
    }

    @SneakyThrows
    @Override
    public void run() {
        while(queue.getCounter() > 0) {
            queue.printPong(Thread.currentThread().getName());
        }
    }
//    @SneakyThrows
//    @Override
//    public void run() {
//        while(!queue.getFlag()) {
//            wait();
//        }
//        queue.print("Pong");
//        queue.setFlag(false);
//        notify();
//    }

}
