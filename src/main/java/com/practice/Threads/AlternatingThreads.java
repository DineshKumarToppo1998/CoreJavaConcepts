package com.practice.Threads;

public class AlternatingThreads {

    private static volatile boolean isOddTurn = true;

    public static void main(String[] args) {
        Object lock = new Object();


        Runnable printOdd = () -> {
            for (int i = 1; i <= 9; i += 2) {
                synchronized (lock) {
                    while (!isOddTurn) {
                        try {
                            lock.wait(); // Lock is with odd thread
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i + " ");
                    isOddTurn = false; //
                    lock.notify();
                }
            }
        };

        Runnable printEven = () -> {
            for (int i = 2; i <= 10; i += 2) {
                synchronized (lock) {
                    while (isOddTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i + " ");
                    isOddTurn = true;
                    lock.notify();
                }
            }
        };

        Thread thread1 = new Thread(printOdd);
        Thread thread2 = new Thread(printEven);

        thread1.start();
        thread2.start();
    }
}