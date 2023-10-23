package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) throws IOException {
        if (!cache.containsKey(key)) {
            put(key, load(key));
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key) throws IOException;

}
