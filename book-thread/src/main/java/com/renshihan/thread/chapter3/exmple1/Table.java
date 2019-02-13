package com.renshihan.thread.chapter3.exmple1;

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
        //使用了Guarded Suspension模式，守护条件是while条件表达式的逻辑非运算 count>0
        //当前桌子上放置的蛋糕个数大于0 作为取蛋糕的take方法的守护条件
        while (count<=0){
            wait();
        }
        String cake=buffer[head];
        head=(head+1)%buffer.length;
        count--;
        //至此，蛋糕已经被拿走了，桌子上的状态发了变化，需要执行notifyAll，唤醒所有正在wait的线程
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " takes "+cake);
        return cake;
    }

}
