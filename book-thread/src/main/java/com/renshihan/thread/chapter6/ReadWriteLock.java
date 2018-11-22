package com.renshihan.thread.chapter6;

/**
 * Created by admin on 2018/11/22.
 * 为了确保安全性，我们必须防止如下两种冲突：
 *  “读取”和“写入”的冲突
 *  “写入”和“写入”的冲突
 */
public class ReadWriteLock {
    private int readingReaders=0;   //正在读取的线程个数
    private int waitingWriters=0;      //正在等待写入的线程个数
    private int writingWriters=0;       //正在写入的线程个数
    private boolean preferWriter=true;      //若写入优先，则为true；
    public synchronized void readLock() throws InterruptedException{
        //当正在写入的线程大于0，或者写入优先并且等待写入的线程》0
        while (writingWriters>0||(preferWriter&&waitingWriters>0)){
            wait();
        }
        readingReaders++;   //正在读取的线程个数+1
    }
    public synchronized void readUnlock(){
        readingReaders--;   //正在读线程数减少1
        preferWriter =true;
        notifyAll();
    }
    /**
     * 用到了before/after模式
     * */
    public synchronized void writeLock() throws InterruptedException{
        waitingWriters++;       //等待写线程数量+1
        try {
            while (readingReaders>0||writingWriters>0){
                wait();
            }
        }finally {
            waitingWriters--;
        }
        writingWriters++;
    }

    public synchronized void writeUnlock(){
        writingWriters--;
        preferWriter=false;
        notifyAll();
    }
}
