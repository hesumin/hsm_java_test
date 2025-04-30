package com.hsm.test.javacn.redisCache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn.redisCache
 * @className:(������): CaffeineCacheConfig
 * @author: Hesumin
 * @createDate: 2025/04/30 14:55
 * @description: TODO һ�仰��������Ĺ���
 */


@Configuration
public class CaffeineCacheConfig extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS) // 10 ������
                .recordStats(); // ��¼����ͳ����Ϣ

        return null;
        //new CaffeineCacheManager("default", caffeine::build);
    }

    @Override
    public CacheResolver cacheResolver() {
        // �Զ��建��������������Ҫ��
        // ...
        return super.cacheResolver();
    }
}

