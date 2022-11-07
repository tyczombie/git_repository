package com.example.jmm;

import java.util.concurrent.locks.LockSupport;

public class VisibilityTest {
    /**
    //第一种方式，用 volatile 修饰 flag，使其具有可见性
    private volatile boolean flag = true;
     */
    private boolean flag = true;
    private int count = 0;
    /**
     //第五种方式，用 volatile 修饰 count，使其具有可见性
     private volatile int count = 0;
     */

    public void refresh(){
        flag = false;
        System.out.println(Thread.currentThread().getName() + " 修改flag：" +  flag);
    }

    public void load(){
        System.out.println(Thread.currentThread().getName() + " 开始执行");
        while(flag){
            count++;
            /**
            //第二种方式 JVM内存屏障
            UnsafeFactory.getUnsafe().storeFence();
             */

            /**
            //第三种方式 释放时间片 上下文切换 加载上下文
            Thread.yield();
             */

            /**
             //第四种方式
            LockSupport.unpark(Thread.currentThread());
             */

            /**
             //第六种方式 内存屏障
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             */
        }
        System.out.println(Thread.currentThread().getName() + " 跳出循环：" +  count);
    }

    public static void main(String[] args) throws Exception{
        VisibilityTest visibilityTest = new VisibilityTest();

        //线程threadA模拟数据加载场景
        Thread threadA = new Thread(() -> visibilityTest.load(), "threadA");
        threadA.start();

        Thread.sleep(1000);

        Thread threadB = new Thread(() -> visibilityTest.refresh(), "threadB");
        threadB.start();
    }

    public void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}
