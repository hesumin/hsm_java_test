package com.hsm.test.byOffer.day0221;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0221
 * @className:(类名称): ReentrantLockDemo  
 * @author: Hesumin
 * @createDate: 2025/02/21 15:50
 * @description: 重入锁
 */

public class ReentrantLockDemo {

    public static int num;

    public static void main(String[] args) throws InterruptedException {
        CountTask countTask = new CountTask();
        Thread t1 = new Thread(countTask);
        Thread t2 = new Thread(countTask);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("num = " + num);
    }

    static class CountTask implements Runnable {

        public ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start~");
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    num++;
                } finally {
                    lock.unlock();
                }
            }
            System.out.println(Thread.currentThread().getName() + " end~");
        }
    }
}
