package com.mike.async.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TestingCompletableFuture {

    /*
     * What is printed is:
     * 
     * 1
     * 5
     * 2
     * 3:Rajeev
     * 4:Hello Rajeev
     * 6
     * 
     * That means that after calling 'supplyAsync' the thread starts immediately ('1' is printed first), and the main thread continues
     * processing without the async thread to finish ('5' is printed). Then, the chained Runnable's continue being processed.
     * 
     * Conclusion: we do not need to call 'CompletableFuture.get()' to obtain the result.
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("1");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("2");
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        }).thenApply(name -> {
            System.out.println("3:" + name);
            return "Hello " + name;
        }).thenApply(greeting -> {
            System.out.println("4:" + greeting);
            return greeting + ", Welcome to the CalliCoder Blog";
        });

        System.out.println("5");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("6");
        //System.out.println(welcomeText.get());
        // Prints - Hello Rajeev, Welcome to the CalliCoder Blog
    }
}
