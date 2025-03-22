package com.hsm.test.byOffer.day0322;

import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0322
 * @className:(类名称): Nowcoder2
 * @author: Hesumin
 * @createDate: 2025/03/22 09:05
 * @description: TODO 一句话描述该类的功能
 */

public class Nowcoder2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 输入数字n
        int n = in.nextInt();
        printTree(n);

        in.close();
    }

    // 打印圣诞树
    public static void printTree(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 0; j < height-1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        int maxStarts = 2*height - 1;
        for (int i = 1; i <= height+1; i++) {
            for (int j = 0; j < height+1-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i*2-1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = 0; i < height; i++) {
            System.out.println("*");
        }
    }
}
