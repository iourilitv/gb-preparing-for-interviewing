package ru.geekbrains.lesson2.hw.task2;

/**
 * Hw for lesson 2.
 * @Author Litvinenko Yuriy
 * 2. Реализовать основные методы ArrayList.
 */
public class Less2Task2 {
    public static void main(String[] args) {
//        MyArrayList<Integer> myArrayList = new MyArrayList<>(20);
        MyArrayList<String> myArrayList = new MyArrayList<>();
        int s = myArrayList.getSize();
        System.out.println(s);

        myArrayList.add("0");
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add(3, "3");
        String el = myArrayList.get(1);

        System.out.println(myArrayList.toString());
        System.out.println("element.value= " + el + ", size= " + myArrayList.getSize());

        String remEl = myArrayList.remove(0);
        System.out.println(myArrayList.toString());
        System.out.println("removed element.value= " + remEl + ", size= " + myArrayList.getSize());

        int lastIndex = myArrayList.getSize();
        for (int i = 0; i < 10; i++) {
            int index = lastIndex + i;
            myArrayList.add(String.valueOf(index));
            System.out.println("index= " + index + ", size= " + myArrayList.getSize());
        }
        System.out.println(myArrayList.toString());
        System.out.println("capacity= " + myArrayList.getCapacity() + ", size= " + myArrayList.getSize());

        myArrayList.clear();
        System.out.println("capacity= " + myArrayList.getCapacity() + ", size= " + myArrayList.getSize());
    }

}
