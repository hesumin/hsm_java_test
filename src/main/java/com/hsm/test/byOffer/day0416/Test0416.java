package com.hsm.test.byOffer.day0416;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0416
 * @className:(类名称): Test0416
 * @author: Hesumin
 * @createDate: 2025/04/16 20:02
 * @description: 买卖股票的最佳时机，返回最大利润
 */

public class Test0416 {
    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        int[] prices2 = new int[]{7,6,4,3,1};
        int[] prices3 = new int[]{1,2,3,4,5,8};

        int ret = Function2(prices3);
        System.out.println(ret);
    }

    // 计算股票最大差值
    private static int Function(int[] prices) {
        // 数组长度 天数
        int length = prices.length;
        int result = 0;
        // 买入
        for (int i = 0; i < prices.length; i++) {
            // 卖出
            for (int j = i+1; j < prices.length; j++) {
                if (prices[j] - prices[i] > result) {
                    result = prices[j] - prices[i];
                }
            }
        }
        return result;
    }

    // 方法2
    private static int Function2(int[] prices) {
        // 数组长度 天数
        int length = prices.length;
        int result = 0;

        //
        for (int i = 1; i < prices.length-1; i++) {
            // i i+1
            if (prices[i-1] < prices[i]) {
                int tmp = prices[i];
                prices[i] = prices[i-1];
                prices[i-1] = tmp;
                if (prices[i+1] - prices[i] > result){
                    result = prices[i+1] - prices[i];
                }
            }
        }
        return result;
    }
}
