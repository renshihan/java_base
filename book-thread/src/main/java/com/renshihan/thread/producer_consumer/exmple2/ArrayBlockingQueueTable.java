package com.renshihan.thread.producer_consumer.exmple2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by admin on 2018/1/3.
 * 使用java.util.concurrent.ArrayBlockingQueue实现桌子
 */
public class ArrayBlockingQueueTable extends ArrayBlockingQueue<String>{

    public ArrayBlockingQueueTable(int capacity) {
        super(capacity);
    }

    @Override
    public void put(String s) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" puts "+s);
        super.put(s);
    }

    @Override
    public String take() throws InterruptedException {
        String cake= super.take();
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        return cake;
    }

}
