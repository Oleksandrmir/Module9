package org.example;

public class MyStack<E> {
    private int size = 0;
    private int capacity = 0;
    private final int CAPACITY = 16;
    private Object[] array;

    /**
     *  задання початкового розміру масиву
     */
    public MyStack() {
        capacity = CAPACITY;
        array = new Object[capacity];
    }

    /**
     *  задання довільного  розміру масиву
     */
    public MyStack(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    /**
     *  Збільшення розміру масиву
     */
    private void increaseCapacity() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
            array[i] = null;
        }
        array = newArray;
    }



    /**
     *  додає елемент до масиву
     */
    public void push(Object value) {
        if (size >= capacity) {
            increaseCapacity();
        }
        array[size++] = value;
    }

    /**
     *  видаляє елемент за індексом
     */
    public E remove(int index) {
        Object o = null;
        if ((index < size) && (index >= 0)) {
            o = get(index);
            shiftToLeft(index);
        }
        return (E) o;
    }

    /**
     *  повертає перший елемент стеку
     */
    public E peek() {
        return (E) array[size - 1];
    }

    /**
     *  повертає перший елемент стеку та видаляє його з колекції
     */
    public E pop() {
        shiftToLeft(0);
        return (E) array[size - 1];
    }

    /**
     *  очищає масив
     */

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     *  повертає розмір масиву
     */

    public int size() {
        return size;
    }

    /**
     *  повертає елемент за індексом
     */

    public E get(int index) {
        if ((index < size) && (index >= 0)) {
            return (E) array[(size - 1) - index];
        }
        return null;
    }

    /**
     *  зменшує масив
     */
    private void shiftToLeft(int start) {
        start = (size - 1) - start;
        size--;
        if (size <= 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(array, start + 1, array, start, size - start);
        }
        array[size] = null;
    }

    /**
     *  чищення масиву від значень "null"
     */
    private void trimToSizeArray() {
        capacity = size + 1;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
