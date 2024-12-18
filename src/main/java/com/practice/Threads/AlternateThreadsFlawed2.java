package com.practice.Threads;

public class AlternateThreadsFlawed2 {
    public static void main(String[] args) {
        Object lock = new Object();

        Runnable oddNumbers = () -> {
            for (int i = 1; i < 9; i += 2) {
                synchronized (lock) {
                    System.out.println(i + " ");
                    lock.notify();


                    try {
                        if (i < 9)
                            lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            }
        };

        Runnable evenNumbers = () -> {
            for (int i = 0; i < 10; i += 2) {
                synchronized (lock) {
                    System.out.println(i + " ");
                    lock.notify();


                    try {
                        if (i < 10)
                            lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            }
        };

        Thread t1 = new Thread(oddNumbers);
        Thread t2 = new Thread(evenNumbers);

        t1.start();
        t2.start();

    }
}
