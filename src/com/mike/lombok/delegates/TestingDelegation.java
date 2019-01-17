package com.mike.lombok.delegates;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

public class TestingDelegation {

    private static interface Generator {
        public void doA();

        public void doB();
    }

    private static class GeneratorBase implements Generator {

        @Override
        public void doA() {
            System.out.println("GeneratorBase::A");
        }

        @Override
        public void doB() {
            System.out.println("GeneratorBase::B");
        }
    }

    @RequiredArgsConstructor
    private static class GeneratorDecorator implements Generator {
        @Delegate(excludes = Add.class)
        private final Generator generator;

        private interface Add {
            void doA();
        }

        @Override
        public void doA() {
            generator.doA();
            System.out.println("GeneratorDecorator::A");
        }
    }

    public static void main(String[] args) {
        Generator decorator = new GeneratorDecorator(new GeneratorBase());
        
        /*
         * GeneratorBase::A
           GeneratorDecorator::A
         */
        decorator.doA();
        
        // GeneratorBase::B
        decorator.doB();
    }
}
