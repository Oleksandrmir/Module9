package org.example.MyHashMap;

public class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;

    public Node() {
    }

    public Node(K key) {
        this.key = key;
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}