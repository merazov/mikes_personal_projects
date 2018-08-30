package com.mike.generics;

public class TrackingSuperclasses {

    private static class InnerClass {

    }

    public static void main(String[] args) {
        InnerClass obj = new InnerClass();
        System.out.println("super=" + obj.getClass().getSuperclass());
    }
}
