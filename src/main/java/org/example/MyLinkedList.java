package org.example;

import java.util.Objects;

public class MyLinkedList<T> {

    static class Node<T> {
        T element;
        Node<T> next;
        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> last;
    private Node<T> first;
    private int size;


    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }


    private Node<T> getNodeByIndex(int index){
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement;
        if (index == 0) {
            removedElement = first.element;
            first = first.next;
            if (first == null){
                last = null;
            }
        } else {
            Node<T> prev = getNodeByIndex(index - 1);
            removedElement = prev.next.element;
            prev.next = prev.next.next;
            if (index == size - 1){
                last = prev;
            }
        }
        size--;
        return removedElement;

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }
}
