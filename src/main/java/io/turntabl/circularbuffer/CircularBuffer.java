package io.turntabl.circularbuffer;

public class CircularBuffer {
    private Object lock  = new Object();
    private volatile Integer[] buffer;
    private volatile int pointer;
    private volatile int oldest;
    private volatile int bufferSize;

    public CircularBuffer(int bufferSize) {
        this.buffer =new Integer[bufferSize];
        this.pointer = 0;
        this.oldest = 0;
        this.bufferSize = bufferSize;
    }

    public boolean isBufferEmpty() { return buffer.length == 0; }
    public int getSize(){ return this.bufferSize; }

    public void insertElement (int data) {
        synchronized (lock) {
            this.buffer[pointer] = data;
            this.pointer = (this.pointer + 1) % buffer.length;
        }
    }

    public int readElement() {
        synchronized (lock){
            if(isBufferEmpty()){
                System.out.println("Buffer is Empty...");
            }else {
                int data = buffer[oldest];
                this.oldest = (this.oldest + 1) % buffer.length;
                return data;
            }
        }

        return 0;
    }

}
