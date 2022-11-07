package com.example.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sum.incrementAndGet();
                }
            });
            thread.start();
        }
        try{
            Thread.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(sum.get());
    }
}
