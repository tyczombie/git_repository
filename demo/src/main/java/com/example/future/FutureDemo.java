package com.example.future;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {

    public static void main(String[] args) {
//        无言同
        Instant now = Instant.now();
        FutureTask task = new FutureTask(new Callable(){
            @Override
            public Object call() throws Exception {
                System.out.println("通过Callable方式执行任务");
                Thread.sleep(3000);
                return "返回任务结果";
            }
        });

        new Thread(task).start();
        try {
            System.out.println("结果:" + task.get());//get()方法是阻塞的，必须等拿到结果再执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("执行时间：" + Duration.between(now, Instant.now()).toMillis());
    }
}
