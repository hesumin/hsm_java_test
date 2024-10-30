package com.hsm.test.javaEE.scheduledTask.kingdee;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlowRuleManager {

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1,
            new NamedThreadFactory("Armor-metrics-record-task",true));

    static {
        startMetricTimerListener();
    }

    public static void startMetricTimerListener() {
        long flushInterval = 3L;
        SCHEDULER.scheduleAtFixedRate(new MetricTimerListener(), 0L, flushInterval, TimeUnit.SECONDS);
    }
}
