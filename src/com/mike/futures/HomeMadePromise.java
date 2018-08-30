package com.mike.futures;

import java.util.function.Consumer;
import java.util.function.Function;

public class HomeMadePromise<R> {

    R container;

    public <T> HomeMadePromise<R> call(Function<T, R> function, T input, Consumer<R> callbackFunction) {
        try {
            // This is not yet simulating the asynchronous nature of getting a container
            container = function.apply(input);

            callbackFunction.accept(container);
            return this;
        } catch (Exception e) {
            throw new RuntimeException("Something bad happened", e);
        }
    }

    public HomeMadePromise<R> then(Consumer<R> afterFunction) {
        
        return this;
    }
    
    public static void main(String[] args) {

    }
}
