package com.hsm.test.ddkk.redis;

import redis.clients.jedis.Jedis;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.ddkk.redis
 * @className:(������): JavaRedisString
 * @author: Hesumin
 * @createDate: 2025/04/22 11:21
 * @description:
 */

public class JavaRedisString {
    public static void main(String[] args) {
        //���ӱ��ص� Redis ����
        Jedis jedis = new Jedis("localhost");
        System.out.println("���ӳɹ�");

        //���� redis �ַ�������
        jedis.set("site", "ddkk.com");

        // ��ȡ�洢�����ݲ����
        System.out.println("redis �洢���ַ���Ϊ: " + jedis.get("site"));

        System.out.println("ɾ��key:" + jedis.del("site"));
    }
}
