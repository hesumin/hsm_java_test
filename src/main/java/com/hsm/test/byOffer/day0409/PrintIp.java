package com.hsm.test.byOffer.day0409;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.dya0409
 * @className:(类名称): Count
 * @author: Hesumin
 * @createDate: 2025/04/09 16:22
 * @description: TODO 一句话描述该类的功能
 */


// 析构函数和构造函数
// 维护令牌桶计数
public class PrintIp {
    public static void main(String[] args) {
        String str = "19216211";
        Function(str);
    }

    public static void Function(String str) {
        // 基本长度检查
        if (str.length() < 4 || str.length() > 12) {
            System.out.println("字符串IP不合法：长度必须在4-12位之间");
            return;
        }

        // 生成所有可能的IPv4组合
        List<String> possibleIPs = generatePossibleIPs(str);

        System.out.println("可能的合法IPv4地址：");
        for (String ip : possibleIPs) {
            System.out.println(ip);
        }
    }

    private static List<String> generatePossibleIPs(String str) {
        List<String> result = new ArrayList<>();
        int len = str.length();

        // 三层循环确定三个点的位置
        for (int i = 1; i < len && i <= 3; i++) {
            for (int j = i + 1; j < len && j <= i + 3; j++) {
                for (int k = j + 1; k < len && k <= j + 3; k++) {
                    // 分割成四部分
                    String part1 = str.substring(0, i);
                    String part2 = str.substring(i, j);
                    String part3 = str.substring(j, k);
                    String part4 = str.substring(k);

                    String ip = part1 + "." + part2 + "." + part3 + "." + part4;
                    result.add(ip);
                }
            }
        }
        return result;
    }
}
