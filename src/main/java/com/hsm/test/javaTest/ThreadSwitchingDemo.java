package com.hsm.test.javaTest;

class NumberPrinter implements Runnable {
    private final String threadName;

    public NumberPrinter(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                long startTime = System.currentTimeMillis(); // 记录开始时间
                // 模拟线程切换，随机休眠
                Thread.sleep((int) (Math.random() * 1000));
                long endTime = System.currentTimeMillis(); // 记录结束时间
                long timeDiff = endTime - startTime; // 计算时间差
                System.out.println(threadName + " slept for: " + timeDiff + " ms");
                System.out.println(threadName + " prints: " + i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ThreadSwitchingDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new NumberPrinter("Thread 1"));
        Thread thread2 = new Thread(new NumberPrinter("Thread 2"));

        // 启动线程
        thread1.start();
        thread2.start();

        // 等待线程结束
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads have finished execution.");
    }
}
