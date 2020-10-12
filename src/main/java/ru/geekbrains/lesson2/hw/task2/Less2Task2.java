package ru.geekbrains.lesson2.hw.task2;

import java.util.*;

/**
 * Hw for lesson 2.
 * @Author Litvinenko Yuriy
 * 2. Реализовать основные методы ArrayList.
 */
public class Less2Task2 {
    public static void main(String[] args) {
//        MyArrayList<Integer> myArrayList = new MyArrayList<>(20);
        MyArrayList<String> myArrayList = new MyArrayList<>();
        int s = myArrayList.size;
        System.out.println(s);

        myArrayList.add("0");
        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add(3, "3");
        String el = myArrayList.get(1);

        System.out.println(myArrayList.toString());
        System.out.println("element.value= " + el + ", size= " + myArrayList.size);

        String remEl = myArrayList.remove(0);
        System.out.println(myArrayList.toString());
        System.out.println("removed element.value= " + remEl + ", size= " + myArrayList.size);

        int lastIndex = myArrayList.size;
        for (int i = 0; i < 10; i++) {
            int index = lastIndex + i;
            myArrayList.add(String.valueOf(index));
            System.out.println("index= " + index + ", size= " + myArrayList.size);
        }
        System.out.println(myArrayList.toString());
        System.out.println("capacity= " + myArrayList.elementData.length + ", size= " + myArrayList.size);

        myArrayList.clear();
        System.out.println("capacity= " + myArrayList.elementData.length + ", size= " + myArrayList.size);
    }

    public static class MyArrayList<E> {
        private static final int DEFAULT_CAPACITY = 10;
        private static final Object[] EMPTY_ELEMENT_DATA = {};
        private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
        private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        private Object[] elementData;
        private int size;

        public MyArrayList() {
            this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
        }

        public MyArrayList(int initialCapacity) {
            if (initialCapacity > 0) {
                this.elementData = new Object[initialCapacity];
            } else if (initialCapacity == 0) {
                this.elementData = EMPTY_ELEMENT_DATA;
            } else {
                throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
            }
        }

        public void add(E element) {
            add(size, element);
        }

        public void add(int index, E element) {
            rangeCheckForAdd(index);

            ensureCapacityInternal(size + 1);
            System.arraycopy(elementData, index, elementData, index + 1,
                    size - index);
            elementData[index] = element;
            size++;
        }

        public E remove(int index) {
            rangeCheck(index);
            E oldValue = elementData(index);
            int numMoved = size - index - 1;
            if (numMoved > 0)
                System.arraycopy(elementData, index + 1, elementData, index,
                        numMoved);
            elementData[--size] = null;
            return oldValue;
        }

        public void clear() {
            for (int i = 0; i < size; i++)
                elementData[i] = null;
            size = 0;
        }

        public E get(int index) {
            rangeCheck(index);
            return elementData(index);
        }

        E elementData(int index) {
            return (E) elementData[index];
        }

        private void rangeCheck(int index) {
            if (index >= size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+size;
        }

        private void rangeCheckForAdd(int index) {
            if (index > size || index < 0)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private void ensureCapacityInternal(int minCapacity) {
            ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
        }

        private void ensureExplicitCapacity(int minCapacity) {
            if (minCapacity - elementData.length > 0)
                grow(minCapacity);
        }

        private static int calculateCapacity(Object[] elementData, int minCapacity) {
            if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            }
            return minCapacity;
        }

        private void grow(int minCapacity) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        private static int hugeCapacity(int minCapacity) {
            if (minCapacity < 0)
                throw new OutOfMemoryError();
            return (minCapacity > MAX_ARRAY_SIZE) ?
                    Integer.MAX_VALUE :
                    MAX_ARRAY_SIZE;
        }

        @Override
        public String toString() {
            return "MyArrayList{" + Arrays.toString(elementData) + "}";
        }
    }

}
