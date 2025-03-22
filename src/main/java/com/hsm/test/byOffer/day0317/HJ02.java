package com.hsm.test.byOffer.day0317;

import java.util.Scanner;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.day0317
 * @className:(类名称): HJ02
 * @author: Hesumin
 * @createDate: 2025/03/18 14:23
 * @description: 二维矩阵中1的反转,找到最少点击次数
 */

public class HJ02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数N和列数M
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用函数计算最少点击次数
        System.out.println(minClicksToZero(matrix));
    }

    public static int minClicksToZero(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int clicks = 0;

        while (true) {
            boolean hasOne = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrix[i][j] == 1) {
                        hasOne = true;
                        flipMatrix(matrix, i, j);
                        clicks++;
                        break;
                    }
                }
                if (hasOne) break;
            }
            if (!hasOne) break;
        }
        return clicks;
    }

    public static void flipMatrix(int[][] matrix, int x, int y) {
        int N = matrix.length;
        int M = matrix[0].length;

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        matrix[x][y] = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < N && newY >= 0 && newY < M && matrix[newX][newY] == 1) {
                matrix[newX][newY] = 0;
            }
        }
    }
}
