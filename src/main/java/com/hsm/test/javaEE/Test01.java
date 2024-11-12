package com.hsm.test.javaEE;

class Parent {
    static {
        long startTime = System.nanoTime();
        System.out.println("父类静态初始化块");
        long endTime = System.nanoTime();
        System.out.println("父类静态初始化块耗时: " + (endTime - startTime) + " 纳秒");
    }

    {
        long startTime = System.nanoTime();
        System.out.println("父类实例初始化块");
        long endTime = System.nanoTime();
        System.out.println("父类实例初始化块耗时: " + (endTime - startTime) + " 纳秒");
    }

    static int staticParentVar = initStaticParentVar();
    int instanceParentVar = initInstanceParentVar();

    Parent() {
        long startTime = System.nanoTime();
        System.out.println("父类构造函数");
        long endTime = System.nanoTime();
        System.out.println("父类构造函数耗时: " + (endTime - startTime) + " 纳秒");
    }

    static int initStaticParentVar() {
        long startTime = System.nanoTime();
        System.out.println("父类静态变量");
        long endTime = System.nanoTime();
        System.out.println("父类静态变量初始化耗时: " + (endTime - startTime) + " 纳秒");
        return 1;
    }

    int initInstanceParentVar() {
        long startTime = System.nanoTime();
        System.out.println("父类实例变量");
        long endTime = System.nanoTime();
        System.out.println("父类实例变量初始化耗时: " + (endTime - startTime) + " 纳秒");
        return 1;
    }
}

class Child extends Parent {
    static {
        long startTime = System.nanoTime();
        System.out.println("子类静态初始化块");
        long endTime = System.nanoTime();
        System.out.println("子类静态初始化块耗时: " + (endTime - startTime) + " 纳秒");
    }

    {
        long startTime = System.nanoTime();
        System.out.println("子类实例初始化块");
        long endTime = System.nanoTime();
        System.out.println("子类实例初始化块耗时: " + (endTime - startTime) + " 纳秒");
    }

    static int staticChildVar = initStaticChildVar();
    int instanceChildVar = initInstanceChildVar();

    Child() {
        long startTime = System.nanoTime();
        System.out.println("子类构造函数");
        long endTime = System.nanoTime();
        System.out.println("子类构造函数耗时: " + (endTime - startTime) + " 纳秒");
    }

    static int initStaticChildVar() {
        long startTime = System.nanoTime();
        System.out.println("子类静态变量");
        long endTime = System.nanoTime();
        System.out.println("子类静态变量初始化耗时: " + (endTime - startTime) + " 纳秒");
        return 1;
    }

    int initInstanceChildVar() {
        long startTime = System.nanoTime();
        System.out.println("子类实例变量");
        long endTime = System.nanoTime();
        System.out.println("子类实例变量初始化耗时: " + (endTime - startTime) + " 纳秒");
        return 1;
    }
}

public class Test01 {

    private String str = "hello";
//    private int count = 2;
//
//
    public void test01() {
        System.out.println("test01");
    }

    public void test02(String str1) {
        System.out.println(str);
        System.out.println("test02: " + str1);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        new Child();
        long endTime = System.nanoTime();
        System.out.println("Child 类实例化总耗时: " + (endTime - startTime) + " 纳秒\n");

        // String 操作
        startTime = System.nanoTime();
        String str = "Hello";
        str = str + " World";  // 创建了一个新的字符串对象 "Hello World"
        System.out.println(str);
        endTime = System.nanoTime();
        System.out.println("String 操作耗时: " + (endTime - startTime) + " 纳秒\n");

        // StringBuffer 操作
        startTime = System.nanoTime();
        StringBuffer sb1 = new StringBuffer("Hello");
        sb1.append(" World");  // 修改了原有对象，而不是创建新的对象
        System.out.println(sb1.append("!"));
        endTime = System.nanoTime();
        System.out.println("StringBuffer 操作耗时: " + (endTime - startTime) + " 纳秒\n");

        // StringBuilder 操作
        startTime = System.nanoTime();
        StringBuilder sb2 = new StringBuilder("Hello");
        sb2.append(" World");  // 修改了原有对象，性能优于 StringBuffer
        System.out.println(sb2.append("!"));
        endTime = System.nanoTime();
        System.out.println("StringBuilder 操作耗时: " + (endTime - startTime) + " 纳秒\n");
    }
}
