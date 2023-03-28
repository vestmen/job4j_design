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
        if (table[bucket(key)] == null) {
            table[bucket(key)] = new MapEntry<>(key, value);
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

    private int bucket(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private boolean keyCompare(K key) {
        MapEntry<K, V> entry = table[bucket(key)];
        return (entry != null
                && Objects.hashCode(key) == Objects.hashCode(entry.key)
                && Objects.equals(key, entry.key));
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        count = 0;
        modCount = 0;
        for (MapEntry<K, V> entry : oldTable) {
            if (entry != null) {
                table[bucket(entry.key)] = entry;
            }
        }
    }

    @Override
    public V get(K key) {
        return keyCompare(key) ? table[bucket(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (keyCompare(key)) {
            table[bucket(key)] = null;
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
