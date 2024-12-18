package com.practice.Threads;

public class AlternatingThreadsFlawed {
    public static void main(String[] args) {
        Object lock = new Object(); // Shared lock for synchronization
        
        Runnable printOdd = () -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    System.out.print(i + " ");
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

        Runnable printEven = () -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    System.out.print(i + " ");
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

        Thread thread1 = new Thread(printOdd);
        Thread thread2 = new Thread(printEven);

        thread1.start();
        thread2.start();
    }
}
