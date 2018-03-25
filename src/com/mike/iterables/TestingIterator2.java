package com.mike.iterables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableCollection;

public class TestingIterator2 {

    /*public static class Integerizer extends ImmutableCollection<Integer> {

        private final Iterable<Integer> inputIterable;

        public Integerizer2(Iterable<Integer> inputIterable) {
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
        
        public void remove() {
            
        }

        @Override
        public boolean contains(Object object) {
            // TODO Auto-generated method stub
            return false;
        }

        boolean isPartialView() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public int size() {
            // TODO Auto-generated method stub
            return 0;
        }
    };

    public static void main(String[] args) {
        List<Integer> itera = new ArrayList<>();
        //.of(1, 2, 10, 9);
        itera.add(1);
        itera.add(2);
        itera.add(10);
        itera.add(9);
        Integerizer integerizer = new Integerizer(itera);
        Integerizer integerizer2 = new Integerizer(itera);
        
        Iterator<Integer> iter = integerizer.iterator();
        while (iter.hasNext()) {
            Integer integer = iter.next();
            System.out.println("int=" + integer);
        }
        itera.remove(0);
        
        iter = integerizer2.iterator();
        while (iter.hasNext()) {
            Integer integer = iter.next();
            System.out.println("int2=" + integer);
        }
        
        /*ImmutableList<Integer> myInts = ImmutableList.of(1, 2, 10, 9);
        Iterable<Integer> itera = myInts;
        Iterator<Integer> iter = itera.iterator();
        while (iter.hasNext()) {
            Integer integer = iter.next();
            System.out.println("int=" + integer);
        }*/
    //}

}
