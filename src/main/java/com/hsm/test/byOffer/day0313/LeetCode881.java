package com.hsm.test.byOffer.day0313;

import java.util.Arrays;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0313
 * @className:(类名称): LeetCode881
 * @author: Hesumin
 * @createDate: 2025/03/13 14:57
 * @description: LeetCode881
 * 第 i 个人的体重为 people[i]，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 */

public class LeetCode881 {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int ans = 0;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;
            }
            r--;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(LeetCode881.numRescueBoats(new int[]{3,2,2,1}, 3));
    }
}
