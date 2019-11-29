package io.turntabl.circularbuffer;

import java.util.stream.IntStream;

public class Consumer extends Thread{
    CircularBuffer buffer;

    public Consumer(CircularBuffer buffer) {
        this.buffer = buffer;
    }

    public void consumeData() {
        int data = buffer.readElement();
        System.out.println("Consumed: " + data);
    }

    public void run() {
        IntStream.range(0, 10).forEach(i -> {
            while (this.buffer.getSize() == 0) {
                Thread.yield();
            }
            this.consumeData();
        });
    }
}
