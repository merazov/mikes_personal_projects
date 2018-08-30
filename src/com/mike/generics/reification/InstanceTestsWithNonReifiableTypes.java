package com.mike.generics.reification;

import lombok.Getter;

public class InstanceTestsWithNonReifiableTypes {

    public static class Base<T> {
        @Getter
        T container;

        public Base(T input) {
            this.container = input;
        }
    }

    public static class Derived1<T> extends Base<T> {
        public Derived1(T input) {
            super(input);
        }
    }

    public static class Derived2<T> extends Base<T> {
        public Derived2(T input) {
            super(input);
        }
    }

    private static void testCasting(Derived1<? extends Number> number) {
        Derived1<Integer> o4 = (Derived1<Integer>) number;
        Integer myInteger = o4.getContainer();
        System.out.println(o4);
    }

    public static void main(String[] args) {
        Derived1<Integer> o1 = new Derived1<>(10);
        Derived1<Double> o2 = new Derived1<>(10.1);

        Object o3 = o1;
        if (o3 instanceof Derived1<?>) {}
        Derived1<?> o4 = (Derived1<?>) o1;
        testCasting(o2);
    }
}
