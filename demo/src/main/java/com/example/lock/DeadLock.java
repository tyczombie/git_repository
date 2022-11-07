package com.example.lock;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DeadLock {

    private static String a = "a";
    private static String b = "b";

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (a){
                System.out.println("threadA进入a同步块，执行中。。。");
                try{
                    Thread.sleep(2000);
                    synchronized (b){
                        System.out.println("threadA进入b同步块，执行中。。。");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"threadA");

        Thread threadB = new Thread(() -> {
            synchronized (b){
                System.out.println("threadB进入b同步块，执行中。。。");
                try{
                    Thread.sleep(2000);
                    synchronized (a){
                        System.out.println("threadB进入a同步块，执行中。。。");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"threadB");

        threadA.start();
        threadB.start();

//        ExecutorService executorService = Executors.newFixedThreadPool(16);
        System.out.println("执行时间:" + Duration.between(LocalTime.now(), Instant.now()).toMillis());
    }
}
