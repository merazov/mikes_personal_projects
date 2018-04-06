package com.mike.reflection.fields;

import static org.apache.commons.lang3.Validate.isTrue;

import java.lang.reflect.Field;

import lombok.RequiredArgsConstructor;

public class AccessingPrivateFields {

    @RequiredArgsConstructor
    private static class MyClass {
        private final String name;
        private final Integer id;
    }
    
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        MyClass myObj = new MyClass("mike", 123);
        
        Field field = myObj.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String myName = (String) field.get(myObj);
        isTrue(myName.equals("mike"));
        System.out.println("name=" + myName);
    }
}
