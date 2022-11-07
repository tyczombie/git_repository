package com.example.lock;

import com.example.jmm.UnsafeFactory;
import sun.misc.Unsafe;

public class CASLock {

    //加锁标记
    private volatile int state;
    private static final Unsafe UNSAFE;
    private static final long OFFSET;

    static{
        try{
            UNSAFE = UnsafeFactory.getUnsafe();
            OFFSET = UnsafeFactory.getFieldOffset(UNSAFE, CASLock.class, "state");
        }catch (Exception e){
            throw new Error(e);
        }
    }

    public boolean cas(){
        //保证了原子性，可见性，有序性，因为在源码上添加了lock前缀指令
        return UNSAFE.compareAndSwapInt(this, OFFSET, 0, 1);
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
    }
}
