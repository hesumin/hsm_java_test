package com.hsm.test.javaEE.redis;

import redis.clients.jedis.Jedis;
/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javaEE.redis
 * @className:(������): JavaRedisDemo
 * @author: Hesumin
 * @createDate: 2025/04/22 11:12
 * @description: JAVA ���ӵ� Redis ����
 */

public class JavaRedisDemo {
    public static void main(String[] args) {
        //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        System.out.println("���ӳɹ�");

        //�鿴�����Ƿ�����
        System.out.println("������������: " + jedis.ping());
    }
}
