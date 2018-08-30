package com.mike.generics;

import java.util.ArrayList;
import java.util.List;

public class StaticMembersInGenericClasses {

    private static class GenericClass<T> {
        private T container;
        private static List<String> cont = new ArrayList<>();

        public GenericClass(T container) {
            this.container = container;
        }

        public static String getClassNameOfContainer() {
            return cont.getClass().getName();
        }
    }

    public static void x(List<Integer>... a) {

    }

    public static void main(String[] args) {
        /*
         * List<Integer> a = Arrays.asList(new Integer[] { 1, 2, 3 });
         * List<Integer> b = Arrays.asList(new Integer[] { 4, 5, 6 });
         * List<List<Integer>> x= Arrays.asList(new List[] { a, b });
         */

        List<Integer> arrayOfIdList[] = new ArrayList[10]; // Suppose generic array creation is legal.
        List<String> nameList = new ArrayList<String>();
        List<? extends Object> objArray[] = arrayOfIdList; // that is allowed because arrays are covariant
        objArray[0] = nameList;
        Integer id = (Integer) objArray[0].get(0);
        
        Class<? extends Integer> c = id.getClass();
    }
}
