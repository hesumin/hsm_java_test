package com.hsm.test.ddkk.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.ddkk.redis
 * @className:(������): RedissonConfig
 * @author: Hesumin
 * @createDate: 2025/04/29 18:00
 * @description: ���� Redis ������Ϣ
 */


@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // Ҳ���Խ� redis ������Ϣ���浽�����ļ�
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
}
