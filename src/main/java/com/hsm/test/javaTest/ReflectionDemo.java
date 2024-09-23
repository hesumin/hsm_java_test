package com.hsm.test.javaTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        // 获取类对象
        Class<?> clazz = Class.forName("com.hsm.test.javaEE.Test01");

        // 获取无参构造器并创建实例
        Constructor<?> constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();

        // 获取并调用方法
        Method method = clazz.getMethod("test01");
        method.invoke(instance);

        // 获取并调用带参数的方法
        Method methodWithParams = clazz.getMethod("test02", String.class);
        methodWithParams.invoke(instance, "Updated Name");

        // 获取字段并修改字段值
        Field field = clazz.getDeclaredField("str");
        field.setAccessible(true);  // 解除私有访问限制
        field.set(instance, "New Name");

        // 获取并调用带参数的方法
        Method methodWithParams1 = clazz.getMethod("test02", String.class);
        methodWithParams1.invoke(instance, "Updated Name");

        // 输出结果
        System.out.println("Updated name: " + field.get(instance));
    }
}

