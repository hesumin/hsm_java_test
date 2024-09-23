package com.hsm.test.spi;

public class MyServiceImpl implements MyService {
    @Override
    public void execute() {
        System.out.println("Executing MyServiceImpl");
    }
}

