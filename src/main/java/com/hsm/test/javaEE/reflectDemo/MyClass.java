package com.hsm.test.javaEE.reflectDemo;

public class MyClass {
    private String secretMethod(String input) {
        return "Hello, " + input;
    }

    public int test(int i) {
        i++;
        return i;
    }

}
