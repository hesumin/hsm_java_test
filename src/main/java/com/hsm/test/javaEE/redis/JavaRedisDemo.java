package com.hsm.test.javaEE.redis;

import redis.clients.jedis.Jedis;
/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javaEE.redis
 * @className:(类名称): JavaRedisDemo
 * @author: Hesumin
 * @createDate: 2025/04/22 11:12
 * @description: JAVA 连接到 Redis 服务
 */

public class JavaRedisDemo {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
    }
}
