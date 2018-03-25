package com.amazon.generics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestingGenericArrayCreation {

    public static void main(String[] args) {
        List<Integer>[] arrayOfIntegerList = new ArrayList<>[10];
        ArrayList<Integer>[] arrayOfIntegerList2 = new ArrayList<>[10];
        BigDecimal[] bigDecimalList = new BigDecimal[10];
    }
}
