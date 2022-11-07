package com.example.sync;

import org.openjdk.jol.info.ClassLayout;

public class ObjectTest {
    static volatile int sum = 0;
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                sum++;
            });
            thread.start();
        }
        Object o = new Test1();
//        Object o = new Object();//16字节
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}

//24字节
class Test{
    private long p;
}

class Test1{
    private boolean flag;
    private long p;
}
