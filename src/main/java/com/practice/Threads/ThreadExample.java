package com.practice.Threads;

public class ThreadExample {
    public static void main(String[] args) {
        Runnable google = new RunnerGoogle();
        Runnable apple = new RunnerApple();

        Thread t1 = new Thread(google);
        Thread t2 = new Thread(apple);

        t1.start();
        t2.start();
    }

}
