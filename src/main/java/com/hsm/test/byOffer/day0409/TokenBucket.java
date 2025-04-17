package com.hsm.test.byOffer.day0409;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.byOffer.dya0409
 * @className:(类名称): TokenBucket
 * @author: Hesumin
 * @createDate: 2025/04/09 17:23
 * @description: 单机版令牌桶实现demo
 */

public class TokenBucket {
    // 桶的容量
    private final long capacity;
    // 令牌放入速率(每秒)
    private final long rate;
    // 当前令牌数量
    private AtomicLong tokens;
    // 上次补充令牌的时间
    private long lastRefillTime;
    // 锁
    private final Lock lock = new ReentrantLock();

    public TokenBucket(long capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = new AtomicLong(capacity);
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    public boolean tryAcquire(long requestedTokens) {
        lock.lock();
        try {
            // 1. 先补充令牌
            refillTokens();

            // 2. 判断是否有足够的令牌
            if (tokens.get() >= requestedTokens) {
                tokens.addAndGet(-requestedTokens);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        // 计算距离上次补充过了多少秒
        long elapsedTime = now - lastRefillTime;
        if (elapsedTime > 0) {
            // 计算需要补充的令牌数
            long refillAmount = elapsedTime * rate / 1000;
            if (refillAmount > 0) {
                lastRefillTime = now;
                // 不能超过桶的容量
                long newTokens = Math.min(tokens.get() + refillAmount, capacity);
                tokens.set(newTokens);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个容量为10，每秒放入2个令牌的桶
        TokenBucket bucket = new TokenBucket(10, 2);

        // 模拟20次请求
        for (int i = 1; i <= 20; i++) {
            if (bucket.tryAcquire()) {
                System.out.println("请求" + i + ": 通过");
            } else {
                System.out.println("请求" + i + ": 被限流");
            }
            // 每隔200毫秒发一次请求
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
