package com.hsm.test.byOffer.day0409;

import java.util.Arrays;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.dya0409
 * @className:(类名称): Test0410
 * @author: Hesumin
 * @createDate: 2025/04/10 19:23
 * @description: TODO 一句话描述该类的功能
 */

public class Test0410 {
    public static void main(String[] args) {
        int[] nums = new int[]{};
        int k = 3;

    }

    private static void function(int[] nums, int k) {
        int length = nums.length;
        // 窗口大小为k,循环次数length-k+1
        for (int i = 0; i < length - k + 1; i++) {
            //int[] temp = nums[i, i +k - 1];
            // 输出窗口中的中位数
            //System.out.println(halfNum(temp));
        }
    }

    // 非有序数组中给出中位数
    private static int halfNum(int[] temp) {
        // lg n
        temp = Arrays.stream(temp).sorted().toArray();
        int len = temp.length;
        if (len % 2 == 0) {
            return (temp[len / 2 - 1] + temp[len / 2]) / 2;
        } else {
            return temp[len / 2 ];
        }
    }
}
