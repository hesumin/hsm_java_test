package com.hsm.test.sign;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author suminhe
 * @version 1.0
 * @date 2025/6/13 16:05
 * @description: todo
 */

public class Test01 {
    public static void main(String[] args) {
        String url = "/api/meta/queryTbFdMetaInfo.do";

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println("timestamp=" + timestamp);

        String appId = "15";
        String appToken = "5cd443da1aed45fd8b388ad82b307f1b";
        appId = "1";
        appToken = "d13003c1308c4af59bc20e63756e73a2";
        // md5(appId + appToken + timestamp + api + jsonStr); jsonStr为参数
        String sign = DigestUtils.md5Hex(url + appId + appToken + timestamp);
        System.out.println("sign=" + sign);
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
