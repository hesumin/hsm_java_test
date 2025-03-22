package com.hsm.test.byOffer.day0322;

import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0322
 * @className:(类名称): Nowcoder1
 * @author: Hesumin
 * @createDate: 2025/03/22 09:04
 * @description: TODO 一句话描述该类的功能
 */

public class Nowcoder1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 输入整数
        int number = in.nextInt();
        // 整数转成字符串
        String string = String.valueOf(number);

        // 字符串长度
        int length = string.length();
        int a = length / 3;
        int b = length % 3;

        // 如果长度小于等于3，则直接输出
        if (length <= 3) {
            System.out.println(string);
            return;
        }

        // 否则分割
        StringBuilder result = new StringBuilder();
        if (b != 0) {
            result.append(string.substring(0,b));
            int i = 1;
            while (i <= a) {
                result.append(",").append(string.substring((i-1)*3+b, i*3+b));
                i++;
            }
        } else {
            result.append(string.substring(0,3));
            int i = 1;
            while (i < a) {
                result.append(",").append(string.substring(i*3, (i+1)*3));
                i++;
            }
        }
        System.out.println(result);

        in.close();
    }
}
