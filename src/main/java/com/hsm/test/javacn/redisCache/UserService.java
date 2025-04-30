package com.hsm.test.javacn.redisCache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn.redisCache
 * @className:(������): UserService
 * @author: Hesumin
 * @createDate: 2025/04/30 14:48
 * @description: TODO һ�仰��������Ĺ���
 */



@Service
public class UserService {

    @Cacheable("users")
    public User findUserById(Long id) {
        // ģ������ݿ��в�ѯ�û�
        return new User(id, "Alice");
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        // ģ������ݿ���ɾ���û�
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        // ģ��������ݿ��е��û���Ϣ
        return user;
    }

/*    @Cacheable(value = "users", key = "#id") // ����������һ����Ϊ"users"�Ļ�������
    public User getUserById(Long id) {
        // ��������ʵ�����ݿ��ѯ��������ʱ����
        return userRepository.findById(id).orElse(null);
    }*/

/*    @CacheEvict(value = "users", key = "#user.id")
    public void updateUser(User user) {
        userRepository.save(user);
    }*/

}
