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

        int ret = Function2(prices);
        System.out.println(ret);
        System.out.println("--------------");
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

    // 方法2：一次遍历（贪心），时间复杂度 O(n)，空间复杂度 O(1)
    // 思路：遍历时维护"历史最低买入价 minPrice"，
    //      每天尝试以当前价格卖出，更新最大利润 result
    private static int Function2(int[] prices) {
        // 边界判断：数组为空或只有1天，无法交易
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = prices[0]; // 历史最低买入价
        int result = 0;           // 最大利润

        for (int i = 1; i < prices.length; i++) {
            // 1. 先尝试在今天卖出，更新最大利润
            if (prices[i] - minPrice > result) {
                result = prices[i] - minPrice;
            }
            // 2. 再更新历史最低买入价，为后续天数做准备
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        return result;
    }
}
