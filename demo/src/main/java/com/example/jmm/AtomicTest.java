package com.example.jmm;

import com.example.lock.CASLock;
import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.locks.ReentrantLock;

public class AtomicTest {

    private volatile static int sum = 0;
    static Object object = "";
    static ReentrantLock lock = new ReentrantLock();
    static CASLock casLock = new CASLock(); //cas不涉及从用户态到内核态的切换

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
//                synchronized (object){
//                    sumFunction();
//                }

//                lock.lock();
//                try{
//                    sumFunction();
//                }finally {
//                    lock.unlock();
//                }

                for(;;){    //自旋获取锁
                    if(casLock.getState() == 0 && casLock.cas()){
                        try{
                            sumFunction();
                        }finally {
                            casLock.setState(0);
                        }
                        break;
                    }
                }


            });
            thread.start();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    public static int sumFunction(){
        for (int j = 0; j < 10000; j++) {
            sum++;
        }
        return sum;
    }
}
