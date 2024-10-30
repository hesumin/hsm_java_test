package com.hsm.test.javaEE.scheduledTask;

public class PrintCurrentTimeTask implements Task{
    @Override
    public void execute() {
        System.out.println("Current time: " + System.currentTimeMillis());
    }
}
