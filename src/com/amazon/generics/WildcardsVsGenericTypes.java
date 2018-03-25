package com.amazon.generics;

import java.util.ArrayList;
import java.util.List;

public class WildcardsVsGenericTypes {

    private static class A {
        public String getName() {
            return "A";
        }
    }
    
    private static class B extends A {
        
    }
    
    public void getA(List<? extends A> list) {
        for (A a : list) {
            System.out.println("name=" + a.getName());
        }
        
        list.add(new A());
    }
    
    public <T extends A> void getA2(List<T> list) {
        for (A a : list) {
            System.out.println("name=" + a.getName());
        }
        
        list.add(new A());
    }
    
    
    public static void main(String[] args) {
        List<? extends A> genericList = new ArrayList<B>();
        A a = new A();
        genericList.add(a);// <-- cannot add
        
        
    }
}
