package com.hsm.test.ddkk.redis;

import redis.clients.jedis.Jedis;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.ddkk.redis
 * @className:(类名称): JavaRedisString
 * @author: Hesumin
 * @createDate: 2025/04/22 11:21
 * @description:
 */

public class JavaRedisString {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        //设置 redis 字符串数据
        jedis.set("site", "ddkk.com");

        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("site"));

        System.out.println("删除key:" + jedis.del("site"));
    }
}
