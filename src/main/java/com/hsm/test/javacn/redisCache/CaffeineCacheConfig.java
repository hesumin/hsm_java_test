package com.hsm.test.javacn.redisCache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javacn.redisCache
 * @className:(类名称): CaffeineCacheConfig
 * @author: Hesumin
 * @createDate: 2025/04/30 14:55
 * @description: TODO 一句话描述该类的功能
 */


@Configuration
public class CaffeineCacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS) // 10 秒后过期
                .recordStats(); // 记录缓存统计信息

        return null;
        //new CaffeineCacheManager("default", caffeine::build);
    }

    @Override
    public CacheResolver cacheResolver() {
        // 自定义缓存解析器（如果需要）
        // ...
        return super.cacheResolver();
    }
}

