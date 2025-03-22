package com.hsm.test.byOffer.day0317;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0317
 * @className:(类名称): HJ02
 * @author: Hesumin
 * @createDate: 2025/03/18 14:20
 * @description: TODO 一句话描述该类的功能
 */

public class HJ03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取任务列表
        String[] taskStrs = scanner.nextLine().split(",");
        int[] tasks = new int[taskStrs.length];
        for (int i = 0; i < taskStrs.length; i++) {
            tasks[i] = Integer.parseInt(taskStrs[i]);
        }

        // 读取冷却时间
        int N = scanner.nextInt();

        System.out.println(minTimeToFinishTasks(tasks, N));
    }

    public static int minTimeToFinishTasks(int[] tasks, int N) {
        // 统计每个任务的数量
        Map<Integer, Integer> taskCounts = new HashMap<>();
        for (int task : tasks) {
            taskCounts.put(task, taskCounts.getOrDefault(task, 0) + 1);
        }

        // 找到最大任务数
        int maxCount = 0;
        for (int count : taskCounts.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        // 计算包含最大任务的周期数
        int cycleLen = maxCount - 1;
        int idleSlots = cycleLen * N;

        // 填充其他任务到冷却时间内
        for (Map.Entry<Integer, Integer> entry : taskCounts.entrySet()) {
            if (entry.getValue() == maxCount) continue; // 跳过最大任务
            idleSlots -= Math.min(entry.getValue(), cycleLen);
        }

        // 返回总时间
        return tasks.length + Math.max(0, idleSlots);
    }
}
