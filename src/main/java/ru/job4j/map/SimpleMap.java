package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int bucket = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        if ((float) count / table.length >= LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = oldTable.length * 2;
        table = new MapEntry[capacity];
        count = 0;
        modCount = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        int bucket = key == null ? 0 : indexFor(hash(key.hashCode()));
        MapEntry<K, V> entry = table[bucket];
        return (entry != null
                && Objects.hashCode(key) == Objects.hashCode(entry.key)
                && Objects.equals(key, entry.key))
                ? entry.value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int bucket = key == null ? 0 : indexFor(hash(key.hashCode()));
        MapEntry<K, V> entry = table[bucket];
        if (entry != null
                && Objects.hashCode(key) == Objects.hashCode(entry.key)
                && Objects.equals(key, entry.key)) {
            table[bucket] = null;
            count++;
            modCount--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                while (point < table.length - 1 && table[point] == null) {
                    point++;
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < table.length && table[point] != null;
            }

            @Override
            public K next() {
                if (!hasNext() || table == null) {
                    throw new NoSuchElementException();
                }
                K rsl = table[point].key;
                point++;
                return rsl;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
