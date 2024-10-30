package com.hsm.test.javaEE.scheduledTask;

import com.hsm.test.javaEE.scheduledTask.kingdee.FlowRuleManager;

import java.util.concurrent.TimeUnit;

public class TaskMainDemo {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // 调度任务
        taskManager.scheduleTask(new PrintCurrentTimeTask(), 0, 5, TimeUnit.SECONDS);
        taskManager.scheduleTask(new PrintMessageTask("Hello World?"), 1, 10, TimeUnit.SECONDS);
        FlowRuleManager.startMetricTimerListener();
        // 运行10秒后关闭调度器
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            taskManager.shutdown();
        }
    }
}
