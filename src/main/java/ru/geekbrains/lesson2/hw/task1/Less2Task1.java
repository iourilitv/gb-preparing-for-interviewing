package ru.geekbrains.lesson2.hw.task1;

/**
 * Hw for lesson 2.
 * @Author Litvinenko Yuriy
 * 1. Реализовать основные методы связанного списка.
 */
public class Less2Task1 {
    public static void main(String[] args) {
        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.add("element1");
        linkedList.add("element2");
        System.out.println(linkedList);

        linkedList.addFirst("elementBefore1");
        System.out.println(linkedList);

        System.out.println("linkedList.add(2, \"InsertedBefore2\")= " + linkedList.add(2, "InsertedBefore2"));
        System.out.println(linkedList);

        System.out.println("linkedList.getFirst()= " + linkedList.getFirst());
        System.out.println("linkedList.getLast()= " + linkedList.getLast());
        System.out.println("linkedList.get(0)= " + linkedList.get(0));
        System.out.println("linkedList.get(1)= " + linkedList.get(1));
        System.out.println("linkedList.get(2)= " + linkedList.get(2));

        System.out.println("linkedList.remove(1)= " + linkedList.remove(1));
        System.out.println("After removing by index 1: " + linkedList);

        System.out.println("linkedList.remove()= " + linkedList.remove());
        System.out.println(linkedList);

        System.out.println("linkedList.removeLast()= " + linkedList.removeLast());
        System.out.println(linkedList);

        linkedList.clear();
        System.out.println(linkedList);
    }

}
