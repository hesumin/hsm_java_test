package com.hsm.test.sign;


import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author suminhe
 * @version 1.0
 * @date 2025/6/13 18:09
 * @description: 使用SHA256算法计算签名
 */

public class Test2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        long now = System.currentTimeMillis();;
        String timestamp = Long.toString(now);
        System.out.println("timestamp");
        System.out.println(timestamp);
        // 随机字符串，十分钟内不重复即可
        String nonce = Long.toHexString(now) + "-" + Long.toHexString((long) Math.floor(Math.random() * 0xFFFFFF));
        System.out.println("nonce");
        System.out.println(nonce);
        // 计算签名并转换为大写
        String token = "Qm7lHlrmjQGhtWe8gKJ5bZBWtg80R8MT";
        String signature = sha256Hex(timestamp + token + nonce + timestamp).toUpperCase();
        System.out.println("signature");
        System.out.println(signature);

        StringBuilder sb = new StringBuilder();
        sb.append("url?timestamp=").append(timestamp).append("&nonce=").append(nonce).append("&signature=")
                .append(signature);
        System.out.println(sb.toString());
    }

    // 使用SHA256算法计算签名
    private static String sha256Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte aHash : encodedHash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main1(String[] args) {
        String url = "http://fitdev.woa.com/permission";
        String uri = "/sa/api/queryApprovalInfoFromWedata.do";
        StringBuilder sb = new StringBuilder(url + uri);
        long timestamp = System.currentTimeMillis() / 1000;
        if (!uri.startsWith("/api/")) {
            uri = uri.substring(uri.indexOf("/api/"));
        }
        String appId = "15";
        String appToken = "5cd443da1aed45fd8b388ad82b307f1b";
        appId = "702";
        appToken = "d4355aaad2bd4929aff7f8b702bbb8ca";
        // md5(appId + appToken + timestamp + api + jsonStr); jsonStr为参数
        String sign = DigestUtils.md5Hex(appId + appToken + timestamp + uri + "{\"processId\":60215,\"processName\":\"wedata_develop_task_deploy_workflow\",\"taskName\":\"huJiaSyncTaskApproval\",\"platformName\":\"pfdd_platform_perssmion\",\"taskId\":205132}");
        sb.append("?appId=").append(appId).append("&timestamp=").append(timestamp).append("&sign=")
                .append(sign);
        System.out.println(sb);
    }
}
