package com.practice.Threads;

import java.util.Arrays;

public class RunnerGoogle implements Runnable{
    @Override
    public void run() {
        System.out.println(Arrays.asList(1,2,3,4,5));
    }
}
