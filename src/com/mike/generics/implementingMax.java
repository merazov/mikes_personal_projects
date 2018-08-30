package com.mike.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class implementingMax {

    public static abstract class Fruit implements Comparable<Fruit> {}

    public static class Apple extends Fruit {
        @Override
        public int compareTo(Fruit o) {
            return 0;
        }
    }

    public static class Orange extends Fruit {
        @Override
        public int compareTo(Fruit o) {
            return 0;
        }
    }

    public static <T extends Comparable<? super T>> T max1(Collection<T> coll) {
        T max = coll.iterator().next();
        for (T element : coll) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static <T extends Fruit> T max2(Collection<T> coll) {
        T max = coll.iterator().next();
        for (T element : coll) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<Orange> oranges = new ArrayList<>();
        List<Fruit> fruits = new ArrayList<>();

        max1(oranges);
        max1(fruits);
        max2(oranges);
        max2(fruits);
    }
}
