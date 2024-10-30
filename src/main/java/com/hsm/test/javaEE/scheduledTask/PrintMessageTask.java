package com.hsm.test.javaEE.scheduledTask;

public class PrintMessageTask implements Task{

    private final String message;

    public PrintMessageTask(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println("Message: " + message);
    }
}
