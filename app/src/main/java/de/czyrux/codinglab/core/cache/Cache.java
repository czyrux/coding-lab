package de.czyrux.codinglab.core.cache;

public interface Cache<K, T>{

    void put(K key, T value);

    T get(K key);
}
