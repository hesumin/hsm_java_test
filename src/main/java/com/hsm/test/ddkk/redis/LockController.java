package com.hsm.test.ddkk.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.ddkk.redis
 * @className:(������): LockController
 * @author: Hesumin
 * @createDate: 2025/04/29 18:17
 * @description: TODO һ�仰��������Ĺ���
 */

@RestController
public class LockController {
    @Autowired
    private RedissonClient redissonClient;
    @GetMapping("/lock")
    public String lockResource() throws InterruptedException {
        String lockKey = "myLock";
        // ��ȡ RLock ����
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // ���Ի�ȡ�������Լ�����������ʱʱ���� 30 �룩
            boolean isLocked = lock.tryLock(30, TimeUnit.SECONDS);
            if (isLocked) {
                // �ɹ���ȡ����
                try {
                    // ģ��ҵ����
                    TimeUnit.SECONDS.sleep(5);
                    return "�ɹ���ȡ������ִ��ҵ�����";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // �ͷ���
                    lock.unlock();
                }
            } else {
                // ��ȡ��ʧ��
                return "��ȡ��ʧ��";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "��ȡ���ɹ�";
    }

    public static void main(String[] args) throws InterruptedException {
        LockController lockController = new LockController();
        String result = lockController.lockResource();

        System.out.println(result);
    }
}
