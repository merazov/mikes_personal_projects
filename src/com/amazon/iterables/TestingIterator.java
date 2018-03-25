package com.amazon.iterables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public class TestingIterator {

    public static class Integerizer implements Iterable<Integer> {

        private final Iterable<Integer> inputIterable;

        public Integerizer(ImmutableCollection<Integer> inputIterable) {
            this.inputIterable = inputIterable;
        }
        
        @Override
        public Iterator<Integer> iterator() {
            Iterator<Integer> it = new Iterator<Integer>() {
                final Iterator<Integer> inputIterator = inputIterable.iterator();

                @Override
                public boolean hasNext() {
                    return inputIterator.hasNext();
                }

                @Override
                public Integer next() {
                    return inputIterator.next();
                }
            };

            return it;
        }
    };

    public static void main(String[] args) {
        List<Integer> itera = new ArrayList<>();
        //.of(1, 2, 10, 9);
        itera.add(1);
        itera.add(2);
        itera.add(10);
        itera.add(9);
        List<Integer> itera3 = ImmutableList.of(1, 2, 10, 9);
        /*Integerizer integerizer = new Integerizer(itera);
        Integerizer integerizer2 = new Integerizer(itera);
        
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                Iterator<Integer> iter = integerizer.iterator();
                while (iter.hasNext()) {
                    Integer integer = iter.next();
                    System.out.println("int=" + integer);
                }
                itera.remove(0);
            }
        };
        
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Iterator<Integer> iter = integerizer2.iterator();
                while (iter.hasNext()) {
                    Integer integer = iter.next();
                    System.out.println("int=" + integer);
                }
            }
        };
        
        thread1.start();
        thread2.start();*/
    }
}
