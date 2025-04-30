package com.hsm.test.javacn.netty;

import io.netty.util.Recycler;

/**
 * @projectName(��Ŀ����): hsm_java_test
 * @package(������): com.hsm.test.javacn.netty
 * @className:(������): UserRecyclerDemo
 * @author: Hesumin
 * @createDate: 2025/04/30 17:01
 * @description: netty ����ػ���ʹ��
 */

public class UserRecyclerDemo {
    private static final Recycler<User> userRecycler = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    static final class User {
        private String name;
        private Recycler.Handle<User> handle;
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public User(Recycler.Handle<User> handle) {
            this.handle = handle;
        }
        public void recycle() {
            handle.recycle(this);
        }
    }

    public static void main(String[] args) {
        User user1 = userRecycler.get(); 	// 1.�Ӷ���ػ�ȡ User ����
        user1.setName("zhangsan"); 			// 2.���� User ���������
        user1.recycle(); 					// 3.���ն��󵽶����
        User user2 = userRecycler.get(); 	// 4.�Ӷ���ػ�ȡ����
        System.out.println(user1 == user2);
        System.out.println(user2.getName());

        user2.setName("lisi");
        System.out.println(user1 == user2);
        System.out.println(user2.getName());
    }
}

