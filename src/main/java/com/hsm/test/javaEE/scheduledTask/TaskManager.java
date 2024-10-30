package com.hsm.test.javaEE.scheduledTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务 ScheduledExecutorService
 */
public class TaskManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void scheduleTask(Task task, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                task.execute();
            } catch (Exception e) {
                System.err.println("Error executing task: " + e.getMessage());
            }
        }, initialDelay, period, unit);
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (Exception e) {
            scheduler.shutdownNow();
        }
    }
}
