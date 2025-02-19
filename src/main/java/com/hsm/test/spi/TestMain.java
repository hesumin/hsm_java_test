package com.hsm.test.spi;

import java.util.ServiceLoader;

public class TestMain {
    public static void main(String[] args) {
        ServiceLoader<MyService> loader = ServiceLoader.load(MyService.class);
        for (MyService service : loader) {
            service.execute();
        }
        System.out.println("TestMain");
    }
}
