package com.hsm.test.ddkk.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.ddkk.redis
 * @className:(类名称): RedissonConfig
 * @author: Hesumin
 * @createDate: 2025/04/29 18:00
 * @description: 配置 Redis 连接信息
 */


@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 也可以将 redis 配置信息保存到配置文件
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }
}
