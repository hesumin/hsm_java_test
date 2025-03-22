package com.hsm.test.byOffer.day0317;

import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0317
 * @className:(类名称): HJ01
 * @author: Hesumin
 * @createDate: 2025/03/18 14:14
 * @description: 字符串句子，指定区间翻转
 */

public class HJ01 {

    public static void main(String[] args) {
        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 读取三个参数
        String content = scanner.nextLine(); // 英文文章内容
        int start = scanner.nextInt();       // 翻转起始下标
        int end = scanner.nextInt();         // 翻转结束下标

        // 分割文章内容为单词数组
        String[] words = content.split(" ");

        // 检查边界条件
        if (start < 0 || end >= words.length || start > end) {
            System.out.println("Invalid indices");
            return;
        }

        // 翻转指定区间内的单词
        reverseWords(words, start, end);

        // 将单词数组重组为字符串并输出
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            result.append(words[i]);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        System.out.println(result.toString());

        // 关闭scanner
        scanner.close();
    }

    /**
     * 翻转指定区间的单词
     * @param words 单词数组
     * @param start 起始下标
     * @param end 结束下标
     */
    private static void reverseWords(String[] words, int start, int end) {
        while (start < end) {
            // 交换两个单词
            String temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
    }

}
