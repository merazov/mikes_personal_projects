package com.mike.generics.reification;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class CastingListOfObjectsToListOfStrings {

    public static List<String> promote(List<Object> objs) {
        for (Object o : objs)
            if (!(o instanceof String))
                throw new ClassCastException();
        return (List<String>) (List<?>) objs; // < unchecked cast
    }

    public static void main(String[] args) {
        List<Object> objs1 = Arrays.<Object>asList("one", "two");
        List<Object> objs2 = Arrays.<Object>asList(1, "two");
        List<String> strs1 = promote(objs1);

        assertTrue((List<?>) strs1 == (List<?>) objs1);

        boolean caught = false;
        try {
            promote(objs2);
        } catch (ClassCastException e) {
            caught = true;
        }
        assert caught;
    }

}
