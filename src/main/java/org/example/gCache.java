package org.example;


import java.util.Map;

public class gCache<T> {
    private final Map<String, T> storage;
    private Class<?> storedType;

    public gCache(Map<String, T> map) {
        this.storage = map;
    }

    public void add(String key, T item) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        storage.put(key, item);
    }

    public T get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        return storage.get(key);
    }

    public void clear() {
        storage.clear();
    }

    public void addAll(gCache<? extends T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Source cache cannot be null");
        }

        if (other.storedType != null && storedType != null
                && !storedType.isAssignableFrom(other.storedType)) {
            throw new IllegalArgumentException("Incompatible cache types: " +
                    other.storedType.getSimpleName() + " cannot be added to " +
                    storedType.getSimpleName());
        }

        storage.putAll(other.storage);
        if (storedType == null && other.storedType != null) {
            storedType = other.storedType;
        }
    }
}