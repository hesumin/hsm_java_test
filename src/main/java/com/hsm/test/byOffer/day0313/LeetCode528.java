package com.hsm.test.byOffer.day0313;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0313
 * @className:(类名称): LeetCode528
 * @author: Hesumin
 * @createDate: 2025/03/13 15:31
 * @description: TODO 一句话描述该类的功能
 */

public class LeetCode528 {
    int[] sum;

    public LeetCode528(int[] w) {
        int n = w.length;
        sum = new int[n + 1];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + w[i - 1];
        }
    }

    public int pickIndex() {
        int n = sum.length;
        int t = (int) ((Math.random() * sum[n - 1]) + 1);
        int l = 1;
        int r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (sum[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return r - 1;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
