package com.mike.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

public class BuilderWithMandatoryFields {

    @Getter
    @Builder(builderMethodName = "innerBuilder")
    public static class MyClass {
        @NonNull
        private final String name;

        public static MyClassBuilder builder(@NonNull final String name) {
            return innerBuilder().name(name);
        }

        private int age;
    }

    @Getter
    public static class MyClass2 {
        @NonNull
        private final String name;

        @Builder(builderMethodName = "privateBuilder")
        private MyClass2(@NonNull final String name) {
            this.name = name;
        }

        public static MyClass2 builder(@NonNull final String name) {
            return privateBuilder().name(name).build();
        }

        private int age;
    }

    public static void main(String[] args) {
        MyClass.builder("mike").age(5).build();
        
        //MyClass2.builder("mike").age(5).build(); //< not allowed
    }
}
