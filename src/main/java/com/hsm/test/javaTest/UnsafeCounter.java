package com.hsm.test.javaTest;

public class UnsafeCounter {
    private int count = 0;

    // Increment method without thread-safe guarantee
    public void increment() {
        count++; // 这行代码不是原子的
    }

    // Get current count
    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        UnsafeCounter counter = new UnsafeCounter();

        // Multiple threads incrementing the counter
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount()); // 结果可能小于 2000
    }
}
