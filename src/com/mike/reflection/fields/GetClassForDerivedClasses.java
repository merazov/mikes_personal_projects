package com.mike.reflection.fields;

public class GetClassForDerivedClasses {

    static class A {
        
    }
    
    static class B extends A {
        
    }

    static class C extends A {
        
    }
    
    public static void main(String[] args) {
        B o1 = new B();
        B o3 = new B();
        C o2 = new C();
        
        System.out.println("B.class" + o1.getClass().getName());
        System.out.println("C.class" + o2.getClass().getName());
        
        if (o1.getClass().equals(o2.getClass())) {
            System.out.println("[1] wrong!");
        } else {
            System.out.println("[1] good!");
        }
        
        if (o1.getClass().equals(o3.getClass())) {
            System.out.println("[2] good!");
        } else {
            System.out.println("[3] wrong!");
        }
        
        /*if (o1.getClass() == o2.getClass()) {
            System.out.println("[2] wrong!");
        } else {
            System.out.println("[2] good!");
        }*/
    }
}
