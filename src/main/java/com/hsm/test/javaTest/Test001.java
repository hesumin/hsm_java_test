package com.hsm.test.javaTest;

import java.util.concurrent.atomic.AtomicInteger;

public class Test001 {
    public static void main(String[] args) {
        System.out.println("Holle world!");

        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(333);
        int i = atomicInteger.get();
        System.out.println(i);

        atomicInteger.addAndGet(2);
    }
}
