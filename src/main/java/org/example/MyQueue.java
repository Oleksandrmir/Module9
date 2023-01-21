package org.example;

public class MyQueue<T> {
    static final class Node<T> {
        private T element;
        private Node<T> next;

        static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        private Node(T element) {
            this.element = element;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public void add(T element) {
        Node<T> newNode = Node.valueOf(element);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T peek() {
        if (first != null) {
            return first.element;
        } else {
            return null;
        }
    }
    public T poll() {
        if (first != null) {
            T element = first.element;
            first = first.next;
            if (first == null) {
                last = null;
            }
            size--;
            return element;
        } else {
            return null;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        first = last = null;
        size = 0;
    }
}
