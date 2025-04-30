package com.hsm.test.javacn.redisCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test
 * @className:(类名称): Application
 * @author: Hesumin
 * @createDate: 2025/04/30 14:44
 * @description: TODO 一句话描述该类的功能
 */

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
