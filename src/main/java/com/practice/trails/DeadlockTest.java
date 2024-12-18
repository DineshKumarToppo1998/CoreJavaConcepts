package com.practice.trails;

class A {
    synchronized void methodA(B b) {
        System.out.println(Thread.currentThread().getName() + ": Holding A, waiting for B");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        } // Introduce a small delay
        synchronized (b) {
            System.out.println(Thread.currentThread().getName() + ": Acquired B");
        }
    }
}

class B {
    synchronized void methodB(A a) {
        System.out.println(Thread.currentThread().getName() + ": Holding B, waiting for A");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        } // Introduce a small delay
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() + ": Acquired A");
        }
    }
}

public class DeadlockTest {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        Thread thread1 = new Thread(() -> {
            a.methodA(b);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            b.methodB(a);
        }, "Thread-2");

        thread1.start();
        thread2.start();

        // Keep the main thread alive for a while to observe the deadlock
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished. Checking if deadlock occurred.");
        if (thread1.isAlive() || thread2.isAlive()) {
            System.out.println("Deadlock likely occurred: Threads are still running.");
        } else {
            System.out.println("No deadlock detected (threads finished).");
        }
    }
}