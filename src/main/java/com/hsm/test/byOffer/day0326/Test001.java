package com.hsm.test.byOffer.day0326;

import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0326
 * @className:(类名称): Test001
 * @author: Hesumin
 * @createDate: 2025/03/26 17:27
 * @description: 给定一组数组和一个值，范围两位数相加等于这个值的数组下标
 */

public class Test001 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int[] nums = {1,2,5,9};
        int target = 6;
        int[] result = function(nums,target);
        System.out.println();

    }

    // 定义函数，返回数组下标
    private static int[] function(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }


}
