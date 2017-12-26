package com.renshihan.javabase.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by admin on 2017/7/11.
 */
public class ActiveThreadTest {
    public static void main(String[] args) {
        ExecutorService exec=null;
        try {
            exec= Executors.newFixedThreadPool(10);
            int activeCount=((ThreadPoolExecutor)exec).getActiveCount();
            System.out.println("active count---"+activeCount);
            exec.execute(new Task());
            exec.execute(new Task());
            exec.execute(new Task());
            activeCount=((ThreadPoolExecutor)exec).getActiveCount();
            System.out.println("active count---"+activeCount);
        }catch (Exception e){

        }finally {
            if(null!=exec){
                exec.shutdown();
            }
        }
    }

}
