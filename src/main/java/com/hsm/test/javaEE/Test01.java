package com.hsm.test.javaEE;

class Parent {
    static {
        System.out.println("父类静态初始化块");
    }

    {
        System.out.println("父类实例初始化块");
    }

    static int staticParentVar = initStaticParentVar();
    int instanceParentVar = initInstanceParentVar();

    Parent() {
        System.out.println("父类构造函数");
    }

    static int initStaticParentVar() {
        System.out.println("父类静态变量");
        return 1;
    }

    int initInstanceParentVar() {
        System.out.println("父类实例变量");
        return 1;
    }
}

class Child extends Parent {
    static {
        System.out.println("子类静态初始化块");
    }

    {
        System.out.println("子类实例初始化块");
    }

    static int staticChildVar = initStaticChildVar();
    int instanceChildVar = initInstanceChildVar();

    Child() {
        System.out.println("子类构造函数");
    }

    static int initStaticChildVar() {
        System.out.println("子类静态变量");
        return 1;
    }

    int initInstanceChildVar() {
        System.out.println("子类实例变量");
        return 1;
    }
}

public class Test01 {
    public static void main(String[] args) {
        new Child();
        System.out.println("test");
    }
}
