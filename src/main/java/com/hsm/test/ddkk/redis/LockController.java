package com.hsm.test.ddkk.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.ddkk.redis
 * @className:(类名称): LockController
 * @author: Hesumin
 * @createDate: 2025/04/29 18:17
 * @description: TODO 一句话描述该类的功能
 */

@RestController
public class LockController {
    @Autowired
    private RedissonClient redissonClient;
    @GetMapping("/lock")
    public String lockResource() throws InterruptedException {
        String lockKey = "myLock";
        // 获取 RLock 对象
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 尝试获取锁（尝试加锁）（锁超时时间是 30 秒）
            boolean isLocked = lock.tryLock(30, TimeUnit.SECONDS);
            if (isLocked) {
                // 成功获取到锁
                try {
                    // 模拟业务处理
                    TimeUnit.SECONDS.sleep(5);
                    return "成功获取锁，并执行业务代码";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            } else {
                // 获取锁失败
                return "获取锁失败";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "获取锁成功";
    }

    public static void main(String[] args) throws InterruptedException {
        LockController lockController = new LockController();
        String result = lockController.lockResource();

        System.out.println(result);
    }
}
