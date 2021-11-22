package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> entry = new MapEntry<>(key, value, hash);
        if (table[index] == null) {
            table[index] = entry;
            count++;
            modCount++;
        }
        return table[index] == entry;
    }

    private int hash(int hashCode) {
        return (hashCode >>> 16) ^ hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            int index = indexFor(entry.hash);
            newTable[index] = entry;
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int hash = hash(key.hashCode());
        int index = indexFor(hash);
        MapEntry<K, V> entry = table[index];
        V value = null;
        if (entry != null && entry.hash == hash && key.equals(entry.key)) {
            value = entry.value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = get(key) != null;
        if (rsl) {
            int index = indexFor(hash(key.hashCode()));
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int count = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (count < table.length && table[count] == null) {
                    count++;
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        int hash;

        public MapEntry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }
}
