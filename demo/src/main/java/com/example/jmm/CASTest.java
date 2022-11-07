package com.example.jmm;

import sun.misc.Unsafe;

public class CASTest {

    public static void main(String[] args) {
        Entity entity = new Entity();
        Unsafe unsafe = UnsafeFactory.getUnsafe();

        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        System.out.println(offset);
        boolean successful;

        //4个参数分别是：对象实例，i段的内存偏移量，字段期望值，字段更新值
        successful = unsafe.compareAndSwapInt(entity, offset, 0, 3);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 3, 5);
        System.out.println(successful + "\t" + entity.x);

        successful = unsafe.compareAndSwapInt(entity, offset, 5, 8);
        System.out.println(successful + "\t" + entity.x);
    }
}

class Entity{
    int y;
    int x;
}
