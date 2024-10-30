package com.hsm.test.javaEE.scheduledTask.kingdee;

public class MetricTimerListener implements Runnable {
    int i = 1;
    @Override
    public void run() {
        System.out.println(i);
        i++;
    }
}
