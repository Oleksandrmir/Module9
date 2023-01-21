package org.example.MyHashMap;

public class MyHashMap<K, V> {

    private Node <K, V>[] table;
    private int capacity = 0;
    private int size;

    private int bucketIndex;
    private final int START_CAPACITY = 16;
    private int tableFullness = 0;
    private int coefficientIncrease = 2;

    /**
     * Конструктор без параметрів
     */
    public MyHashMap() {
        this.capacity = START_CAPACITY;
        table = (Node<K, V>[]) new Node[capacity];
    }

    /**
     * конструктор з кількістю сегментів
     */
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        table = (Node<K, V>[]) new Node[capacity];
    }

    /**
     * хеш-код для ключа
     */

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * визначення індексу для сегмента
     */

    private int indexFor(int h, int capacity) {
        return h & (capacity - 1);
    }

    /**
     * помістити Node у сегмент. Якщо сегмент не порожнє, зв’язаний список буде створено в цьому сегменті
     */
    public void put(K key, V value) {

        if (tableFullness >= (capacity)) {
            table = increaseTable(table);
        }
        bucketIndex = indexFor(hash(key), capacity);                                    //визначення індексу сегмента

        Node<K, V> temp = table[bucketIndex];
        Node<K, V> newNode = new Node<>(key, value);

        if (table[bucketIndex] == null) {                                                   // якщо сегмент порожній
            table[bucketIndex] = newNode;
            tableFullness++;
            size++;
        } else {                                                                            // якщо сегмент не порожній
            while (temp.next != null) {
                if (newNode.key.equals(temp.key)) {
                    temp.value = newNode.value;
                    return;
                } else {
                    temp = temp.next;                                                        // node назад
                }
            }
            if (newNode.key.equals(temp.key)) {                                             //node.next == null && key == key
                temp.value = newNode.value;
                return;
            }
            temp.next = newNode;                                                            // визначення в кінці
            size++;
        }
    }

    /**
     * отримати Value by Key
     */
    public V get(K key) {
        bucketIndex = indexFor(hash(key), capacity);
        Node<K, V> temp = table[bucketIndex];
        if (key.equals(temp.key)) {
            return temp.value;
        }
        while (table[bucketIndex].next != null) {
            if (key.equals(temp.key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return temp.value;
    }

    /**
     * видалити Node by Key
     */
    public boolean remove(K key) {
        bucketIndex = indexFor(hash(key), capacity);
        Node<K, V> temp = table[bucketIndex];
        if (key.equals(temp.key)) {
            if (temp.next == null) {
                table[bucketIndex] = null;
                size--;
                return true;
            }
        }
        while (temp.next != null) {
            if (key.equals(temp.next.key)) {
                temp.next = temp.next.next;
                size--;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    /**
     * очистити
     */
    public void clear() {
        table = (new MyHashMap<K, V>()).table;
    }

    /**
     * Зібльшити розмір
     */
    private Node<K, V>[] increaseTable(Node<K, V>[] table) {
        tableFullness = 0;
        capacity = table.length * coefficientIncrease;
        MyHashMap<K, V> tempHashMap = new MyHashMap<>(capacity);
        for (Node<K, V> data : table) {
            if (data != null) {
                while (data.next != null) {
                    tempHashMap.put(data.key, data.value);
                    data = data.next;
                }
                tempHashMap.put(data.key, data.value);
            }
        }
        tableFullness = tempHashMap.tableFullness;
        return tempHashMap.table;
    }
}
