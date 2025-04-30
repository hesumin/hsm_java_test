package com.hsm.test.javacn.redisCache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @projectName(项目名称): hsm_java_test
 * @package(包名称): com.hsm.test.javacn.redisCache
 * @className:(类名称): UserService
 * @author: Hesumin
 * @createDate: 2025/04/30 14:48
 * @description: TODO 一句话描述该类的功能
 */



@Service
public class UserService {

    @Cacheable("users")
    public User findUserById(Long id) {
        // 模拟从数据库中查询用户
        return new User(id, "Alice");
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        // 模拟从数据库中删除用户
    }

    @CachePut(value = "users", key = "#user.id")
    public User updateUser(User user) {
        // 模拟更新数据库中的用户信息
        return user;
    }

/*    @Cacheable(value = "users", key = "#id") // 假设我们有一个名为"users"的缓存区域
    public User getUserById(Long id) {
        // 这里是真实的数据库查询或其他耗时操作
        return userRepository.findById(id).orElse(null);
    }*/

/*    @CacheEvict(value = "users", key = "#user.id")
    public void updateUser(User user) {
        userRepository.save(user);
    }*/

}
