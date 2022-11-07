package com.example.jmm;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.w3c.dom.ls.LSOutput;

public class SyncDemo {

    //共享资源，临界资源
    private static int counter = 0;

    /**
    //临界区
    public static synchronized void increment(){
        counter++;
    }

    //临界区
    public static synchronized void decrement(){
        counter--;
    }
     */

    static String lock = "";
    public static void increment(){
        synchronized(lock){
            counter++;
        }
    }

    //临界区
    public static void decrement(){
        synchronized(lock){
            counter--;
        }
    }



    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        }, "t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                decrement();
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("counter = " + counter);
    }


}
