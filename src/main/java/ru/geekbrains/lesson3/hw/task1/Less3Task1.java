package ru.geekbrains.lesson3.hw.task1;

/**
 * Hw for lesson 3.
 * @Author Litvinenko Yuriy
 * 1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
 */
public class Less3Task1 {
    public static void main(String[] args) {
        PingPongQueue queue = new PingPongQueue();
        new Ping(queue);
        new Pong(queue);
    }

}
