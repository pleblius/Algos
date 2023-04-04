package assign09;

import java.util.Arrays;

public class MyHashMap<K, V> {

    private Entry<K, V>[] table;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            return; // don't allow null keys
        }
        int hash = key.hashCode() % capacity;
        Entry<K, V> entry = new Entry<>(key, value);
        if (table[hash] == null) {
            table[hash] = entry;
        } else {
            Entry<K, V> prev = null;
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // update existing key's value
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = entry; // add new key-value pair to the end of the chain
        }
        size++;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }
        int hash = key.hashCode() % capacity;
        Entry<K, V> current = table[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // key not found
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        int hash = key.hashCode() % capacity;
        Entry<K, V> prev = null;
        Entry<K, V> current = table[hash];
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[hash] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
