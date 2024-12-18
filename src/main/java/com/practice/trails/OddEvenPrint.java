package com.practice.trails;

import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenPrint {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final int MAX_NUM = 10;

    public static void main(String[] args) {
        Thread oddThread = new Thread(() -> {
            while (counter.get() <= MAX_NUM) {
                synchronized (counter) {
                    int current = counter.get();
                    if (current % 2 != 0) {
                        System.out.println(current);
                        counter.incrementAndGet();
                        counter.notify();
                    } else {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread evenThread = new Thread(() -> {
            while (counter.get() <= MAX_NUM) {
                synchronized (counter) {
                    int current = counter.get();
                    if (current % 2 == 0) {
                        System.out.println(current);
                        counter.incrementAndGet();
                        counter.notify();
                    } else {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        oddThread.start();
        evenThread.start();
    }
}