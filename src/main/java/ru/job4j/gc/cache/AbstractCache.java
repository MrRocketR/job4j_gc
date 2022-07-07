package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> file = new SoftReference<>(value);
        cache.put(key, file);
    }

    public V get(K key) {
        V valueStrong = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (valueStrong == null) {
            valueStrong =  load(key);
            put(key, valueStrong);
        }
        return valueStrong;
    }

    protected abstract V load(K key);

}

