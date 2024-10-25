package com.hsm.test.javaEE.reflectDemo;

import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) {
        try {
            MyClass myClass = new MyClass();

            // 获取私有方法
            Method method = MyClass.class.getDeclaredMethod("secretMethod", String.class);
            // 设置可访问性
            method.setAccessible(true);

            // 调用私有方法
            String result = (String) method.invoke(myClass, "World");
            System.out.println(result); // 输出: Hello, World
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
