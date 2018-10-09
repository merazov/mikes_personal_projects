package com.mike.async.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import lombok.Data;

public class CombiningTwoDependentCompletableFutures {

    @Data
    public static class User {
        private final String id;
    }

    public CompletableFuture<User> getUserDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("2");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new User(userId);
        });
    }

    public CompletableFuture<Double> getCreditRating() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("3");
            return 740D;
        });
    }

    /*
     *  This is printed since the second task is dependent on the first
     *  1
        2
        3
        4 result:java.util.concurrent.CompletableFuture@214c265e[Completed normally]

     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CombiningTwoDependentCompletableFutures obj = new CombiningTwoDependentCompletableFutures();

        System.out.println("1");
        CompletableFuture<CompletableFuture<Double>> result = obj.getUserDetail("mikeid")
                .thenApply(user -> obj.getCreditRating());
        System.out.println("4 result:" + result.get());
    }
}
