package com.mike.generics;

import com.google.common.reflect.TypeToken;

import lombok.Getter;

public class TypeTokenTests {

    @Getter
    private static abstract class IKnowMyType<T> {

        @SuppressWarnings("serial")
        private TypeToken<T> type = new TypeToken<T>(this.getClass()) {};

        public Class<?> getClassX() {
            return getClass();
        }
    }
    
    public static void main(String[] args) {
        final IKnowMyType<String> obj = new IKnowMyType<String>() {};

        System.out.println(obj.getType()); // java.lang.String since that was the type parameter passed

        System.out.println(obj.getClassX()); //class com.mike.generics.TypeTokenTests$3 since was create as an anonymous class
    }
}
