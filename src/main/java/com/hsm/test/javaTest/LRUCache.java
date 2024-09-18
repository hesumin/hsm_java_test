package com.hsm.test.javaTest;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    // Constructor to initialize capacity
    public LRUCache(int capacity) {
        // Initialize LinkedHashMap with accessOrder as true for LRU behavior
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Remove the eldest entry if the cache size exceeds the capacity
        return size() > capacity;
    }

    public static void main(String[] args) {
        // Create an LRU Cache with capacity 3
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);

        // Add some elements to the cache
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");

        // Display the cache
        System.out.println("Cache: " + lruCache);

        // Access some elements to change their order
        lruCache.get(1);
        lruCache.put(4, "Four"); // This will remove the least recently used entry

        // Display the cache after adding new element
        System.out.println("Cache after adding 4: " + lruCache);

        // Accessing an entry
        System.out.println("Accessing element 2: " + lruCache.get(2));

        // Display the cache after accessing element 2
        System.out.println("Cache after accessing 2: " + lruCache);

        lruCache.get(3);
        System.out.println(lruCache);

        lruCache.put(2, "Two");
        System.out.println(lruCache);
    }
}
