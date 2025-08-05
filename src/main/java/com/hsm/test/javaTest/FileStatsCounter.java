package com.hsm.test.javaTest;


/**
 * @author suminhe
 * @version 1.0
 * @date 2025/8/1 16:52
 * @description: todo
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class FileStatsCounter {

    public static void main(String[] args) {
        // 默认统计同目录下的example.txt文件
        String defaultFileName = "wedata_download_analyse.txt";
        String filePath;

        // 如果通过命令行参数指定了文件，使用参数中的文件
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = defaultFileName;
            System.out.println("未指定文件，将尝试统计同目录下的 " + defaultFileName);
        }

        File file = new File(filePath);

        // 显示当前工作目录（方便调试）
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));

        if (!file.exists()) {
            System.out.println("文件不存在: " + filePath);
            System.out.println("请确保文件存在于程序所在目录");
            return;
        }

        try {
            // 统计字节长度
            long byteLength = file.length();

            // 统计行数（文本长度）
            long lineCount = Files.lines(file.toPath()).count();

            // 统计字符长度
            long charCount = 0;
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    charCount += line.length();
                    // 加上换行符（每行有一个换行符，除了最后一行）
                    if (reader.ready()) {
                        charCount += System.lineSeparator().length();
                    }
                }
            }

            // 输出结果
            System.out.println("\n文件统计结果:");
            System.out.println("文件路径: " + file.getAbsolutePath());
            System.out.println("文本长度（行数）: " + lineCount);
            System.out.println("字符长度: " + charCount);
            System.out.println("字节长度: " + byteLength + " bytes");

        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }
}
