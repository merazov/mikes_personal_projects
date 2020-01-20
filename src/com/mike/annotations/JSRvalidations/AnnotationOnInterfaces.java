package com.mike.annotations.JSRvalidations;

import lombok.NonNull;

public class AnnotationOnInterfaces {

    public static interface MyInterface {
        public void test(@NonNull final String msg);
    }
    
    public static class MyImplementation implements MyInterface {

        @Override
        public void test(String msg) {
            msg = "";
            System.out.println("msg:" + msg); //< not enforced!
        }
    }
    
    public static void main(String[] args) {
        MyImplementation impl = new MyImplementation();
        impl.test(null);
    }
}
