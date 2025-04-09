package com.hsm.test.byOffer.day0331;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0331
 * @className:(类名称): Test01
 * @author: Hesumin
 * @createDate: 2025/03/31 14:16
 * @description: TODO 一句话描述该类的功能
 */

public class Test01 {
    public static void main(String[] args) {
        List<int[]> follows = Arrays.asList(
                new int[]{1,2},
                new int[]{1,3},
                new int[]{2,3},
                new int[]{3,4},
                new int[]{4,5},
                new int[]{5,4},
                new int[]{5,3}
        );
        int k = 2;
        List<Map.Entry<Integer, Integer>> sortedList = findfluencers(follows, k);
        System.out.println();
    }

    // 执行函数
    public static List<Map.Entry<Integer, Integer>> findfluencers(List<int[]> follows, int k) {
        // ArrayDeque

        // 用map统计userid及数量
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < follows.size(); i++) {
            int key = follows.get(i)[0];
            if (!count.containsKey(key)) {
                count.put(key, 1);
            } else {
                int value = count.get(key) + 1;
                count.put(key, value);
            }
        }
        // 将map转换为list，然后根据值降序排序
        List<Map.Entry<Integer, Integer>> sortedList = count.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(k)
                .collect(Collectors.toList());


        return sortedList;
    }
}
