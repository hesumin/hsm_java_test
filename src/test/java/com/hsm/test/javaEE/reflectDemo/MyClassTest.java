package com.hsm.test.javaEE.reflectDemo;


import com.hsm.test.javaEE.reflectDemo.MyClass;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class MyClassTest {

    @Test
    public void testSecretMethod() throws Exception {
        MyClass myClass = new MyClass();

        // 获取私有方法
        Method method = MyClass.class.getDeclaredMethod("secretMethod", String.class);
        method.setAccessible(true);

        // 调用私有方法并断言结果
        String result = (String) method.invoke(myClass, "JUnit");
        System.out.println(result);
        assertEquals("Hello, JUnit", result);
    }
}

