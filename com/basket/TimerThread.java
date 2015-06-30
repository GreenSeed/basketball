package com.basket;

public class TimerThread extends Thread {
    private Screen s;

    public volatile double start;
    public volatile double now;

    public TimerThread(Screen s) {
        this.s = s;
        this.reset();
    }

    @Override
    public void run() {
      reset();
        while(true) {
            now = (System.nanoTime() - start)/1000000000.0;
            s.newTime();
            try {
                sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset() {
        start = System.nanoTime();
    }
}
