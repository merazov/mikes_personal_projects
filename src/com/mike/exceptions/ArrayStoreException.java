package com.mike.exceptions;

public class ArrayStoreException {

    public void throwsArrayStoreException() {
        Object[] o = "a;b;c".split(";");
        o[0] = 42;
    }

    public static void main(String[] args) {
        ArrayStoreException sut = new ArrayStoreException();
        sut.throwsArrayStoreException();
    }
}
