package com.renshihan.thread.producer_consumer;

/**
 * Created by admin on 2017/12/30.
 * 桌子
 */
public class Table {
    private final String[] buffer;
    private int tail;       //下次put的位置
    private int head;       //下次take的位置
    private int count;      //buffer中的蛋糕个数

    public Table(int count) {
        this.buffer = new String[count];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }
    //放置蛋糕
    public synchronized void put(String cake) throws InterruptedException{
        System.out.println(Thread.currentThread().getName()+" puts "+cake);
        while (count>=buffer.length){
            wait();
        }
        buffer[tail]=cake;
        tail=(tail+1)%buffer.length;
        count++;
        notifyAll();
    }
    //拿去蛋糕
    public synchronized String take()throws InterruptedException{
        while (count<=0){
            wait();
        }
        String cake=buffer[head];
        head=(head+1)%buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes "+cake);
        return cake;
    }

}
