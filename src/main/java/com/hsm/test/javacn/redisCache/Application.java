package com.hsm.test.javacn.redisCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test
 * @className:(������): Application
 * @author: Hesumin
 * @createDate: 2025/04/30 14:44
 * @description: TODO һ�仰��������Ĺ���
 */

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
