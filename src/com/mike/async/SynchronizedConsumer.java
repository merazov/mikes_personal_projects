package com.mike.async;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SynchronizedConsumer {

    private final List<String> objects;
    private final List<String> objectsForConsumerWithWaits;
    int counter = 0;

    public SynchronizedConsumer() {
        objects = new ArrayList<>();
        objects.add("a");
        objects.add("b");
        objects.add("c");
        objects.add("d");
        objects.add("e");
        
        counter = 0;

        objectsForConsumerWithWaits = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (objectsForConsumerWithWaits) {
               // if (objectsForConsumerWithWaits.isEmpty()) {
                    objectsForConsumerWithWaits.add(Integer.toString(counter));
                    /*objectsForConsumerWithWaits.add("6");
                    objectsForConsumerWithWaits.add("7");
                    objectsForConsumerWithWaits.add("8");
                    objectsForConsumerWithWaits.notifyAll();*/
                    objectsForConsumerWithWaits.notify();
              //  }
                counter++;
            }

            Thread.sleep(100);
        }
    }

    public void consumerWithWait() throws InterruptedException {
        while (true) {
            //System.out.println("[" + Thread.currentThread().getName() + "]");

            String obj = null;
            synchronized (objectsForConsumerWithWaits) {
                while (objectsForConsumerWithWaits.isEmpty()) {
                    System.out.println("[" + Thread.currentThread().getName() + "] about to go to wait");
                    objectsForConsumerWithWaits.wait();
                }

                //System.out.println("[" + Thread.currentThread().getName() + "] got the lock");

                
                if (!objectsForConsumerWithWaits.isEmpty()) {
                    System.out.println("[" + Thread.currentThread().getName() + "] about to consume");
                    obj = objectsForConsumerWithWaits.get(0);
                    objectsForConsumerWithWaits.remove(0);
                }
            }
                    
            Thread.sleep(20000);
            System.out.println("[" + Thread.currentThread().getName() + "] ^^^ consumed: " + obj);
            
        }
    }

    public void consumerWithoutWait() {
        String dequeued = null;
        synchronized (objects) {
            Iterator<String> iterator = objects.iterator();
            if (iterator.hasNext()) {
                dequeued = iterator.next();
                iterator.remove();
            }
            // objects.remove(0);
        }
        // if (dequeued != null) {
        System.out.println(dequeued);
        // }
    }

    public static void main(String[] args) throws InterruptedException {

        /*
         * for (int j = 0; j < 1000; j++) {
         * final int numThreads = 10;
         * 
         * SynchronizedConsumer sut = new SynchronizedConsumer();
         * Thread[] threads = new Thread[numThreads];
         * for (int i = 0; i < numThreads; i++) {
         * threads[i] = new Thread() {
         * 
         * @Override
         * public void run() {
         * sut.consumerWithoutWait();
         * }
         * };
         * threads[i].start();
         * }
         * 
         * for (int i = 0; i < numThreads; i++) {
         * threads[i].join();
         * }
         * 
         * sut.consumerWithoutWait();
         * }
         */

        //System.out.println("1");
        SynchronizedConsumer sut = new SynchronizedConsumer();
        Thread producer = new Thread() {
            @Override
            public void run() {
                try {
                    sut.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //System.out.println("2");

        Thread[] consumers = new Thread[5];
        for (int i = 0; i < 5; i++) {
            consumers[i] = new Thread("Thread:" + i) {
                @Override
                public void run() {
                    try {
                        sut.consumerWithWait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        
        for (int i = 0; i < 5; i++) {
            consumers[i].start();
        }
        Thread.sleep(1000);
        producer.start();

        System.out.println("3");

        producer.join();
        for (int i = 0; i < 5; i++) {
            consumers[i].join();
        }

        System.out.println("4");
    }
}
