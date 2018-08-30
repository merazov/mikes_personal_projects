package com.mike.generics.reification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.Validate;

public class CollectionToArray {

    public static <T> T[] toArray(Collection<T> coll) {
        T[] ret = (T[]) new Object[coll.size()];
        int i = 0;
        for (T elem : coll) {
            ret[i++] = elem;
        }
        return ret;
    }

    public static <E> List<E> singleton(E elt) {
        return Arrays.asList(elt);  
      // generic array creation
      }
    
    public static void main(String[] args) {
        /* Class<? extends String> z = "mike".getClass();
        // org.apache.commons.lang3.Validate.isInstanceOf(Class<String>.class, z);
        String w = "mike";

        List<Integer>[] intLists = (List<Integer>[]) new List[] { Arrays.<Integer>asList(1) }; // unchecked cast
        List<? extends Number>[] numLists = intLists;
        numLists[0] = Arrays.asList(1.01);
        int n = intLists[0].get(0); // class cast exception!
        System.out.println(n);

        List<String> strings = Arrays.asList("one", "two");
        String[] a = toArray(strings); // < class cast error
        System.out.println("a=" + a);
        */
        /*List<Integer> a = Arrays.asList(new Integer[] { 1, 2, 3 });
        List<Integer> b = Arrays.asList(new Integer[] { 4, 5, 6 });
        List<List<Integer>> x = Arrays.asList(new List[]{ a, b });*/
        // generic array creation
        
        List<Integer> c = new ArrayList<>();
        List<String> d = new ArrayList<>();
        Validate.isTrue(c.getClass().equals(d.getClass()));
        Validate.isTrue(c.getClass() == d.getClass());
        
        List<Integer> ints = new ArrayList<Integer>();
        Class<? extends List> k = ints.getClass();
        assert k == ArrayList.class;
        Class<List> abc = List.class;
        
        Class<?> obj = int.class;
    }
}
