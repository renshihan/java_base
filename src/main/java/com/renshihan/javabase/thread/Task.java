package com.renshihan.javabase.thread;

/**
 * Created by admin on 2017/7/11.
 */
public class Task implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
