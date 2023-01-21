package org.example;

/**
 * ArrayList
 */
public abstract class MyArrayList<E>  {
    private int size = 0;
    private int capacity = 0;
    private final int CAPACITY = 16;
    private Object[] array;

    /**
     *  задання початкового розміру масиву
     */
    public MyArrayList() {
        capacity = CAPACITY;
        array = new Object[capacity];
    }

    /**
     *  задання довільного  розміру масиву
     */
    public MyArrayList(int capacity) {
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
    public boolean add(Object value) {
        if (size >= capacity) {
            increaseCapacity();
        }
        array[size++] = value;
        return true;
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
            return (E) array[index];
        }
        return null;
    }

    /**
     *  зменшує масив
     */
    private void shiftToLeft(int start) {
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