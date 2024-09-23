package com.hsm.test.javaTest;

public class VolatileExample {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            System.out.println(running);
            while (running) {
                // 执行某些任务
                System.out.println("test111");
            }
            System.out.println("Thread stopped.");
        });

        worker.start();

        // 模拟其他操作
        try {
//            worker.wait();
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        running = false; // 修改 volatile 变量
    }
}

