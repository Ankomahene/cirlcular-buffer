package io.turntabl.circularbuffer;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CircularBuffer buffer = new CircularBuffer(10);

        Producer producer = new Producer (buffer);
        Consumer consumer = new Consumer (buffer);

        producer.start();
        Thread.sleep(5000);
        System.out.println("===============");
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            System.err.println("Error with Threads");
            e.printStackTrace();
        }

    }
}
