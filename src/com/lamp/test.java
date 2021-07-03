package com.lamp;

import java.lang.reflect.Field;

public class test {
    String name;

    public static void main(String[] args) throws IllegalAccessException {
        test test = new test();
        Class<? extends test> aClass =test .getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {

            System.out.println(declaredField);
            declaredField.set(test,"faewf");
        }
        System.out.println(test.name);
    }
}
