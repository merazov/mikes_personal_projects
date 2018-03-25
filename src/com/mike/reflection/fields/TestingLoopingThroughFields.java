package com.mike.reflection.fields;

import java.lang.reflect.Field;
import java.util.List;

import lombok.Getter;

public class TestingLoopingThroughFields {

    public static class TestClass<T> {

        private final Class<T> myClass;

        public TestClass(Class<T> myClass) {
            this.myClass = myClass;
        }
        
        public void transform() {
            for (Field field : myClass.getDeclaredFields()) {
                System.out.println("field:" + field.getName() + " clazz=" + field.getType());
                Class<?> fieldDeclaredClazz = field.getType();
                if (fieldDeclaredClazz.equals(String.class)) {
                    System.out.println("this is a String");
                } else if (fieldDeclaredClazz.equals(List.class)) {
                    System.out.println("this is a List");
                } else {
                    System.out.println("?");
                }
            }
        }
    }

    @Getter
    public static class Pojo {
        private String name;
        private String job;
        private List<Integer> myFamilyMembers;
    }

    public static void main(String[] args) {
        TestClass<Pojo> obj = new TestClass<Pojo>(Pojo.class);
        obj.transform();
    }
}
