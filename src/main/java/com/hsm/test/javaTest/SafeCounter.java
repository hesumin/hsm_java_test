package com.hsm.test.javaTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 */
public class SafeCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    // Increment method with thread-safe guarantee
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    // Get current count
    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SafeCounter counter = new SafeCounter();

        // Multiple threads incrementing the counter
        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 50; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}

