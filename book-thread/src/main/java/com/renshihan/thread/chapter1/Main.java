package com.renshihan.thread.chapter1;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by admin on 2017/12/26.
 * 计数信号量   确保某个区域“最多只能由N个线程”执行
 */
class BoundedResource {
    private final Semaphore semaphore;
    private final int permits;
    private final static Random random=new Random(314159);
    //构造函数（permits为资源个数）
    public BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
        this.permits = permits;
    }

    //使用资源
    public void use() throws InterruptedException{
        semaphore.acquire();    //是否确实存在可用资源，当所有资源都已被使用时，线程会阻塞在该方法中。
        //当acquire()返回时，一定存在可用资源，线程随后将调用doUse方法，并最后执行semaphore.release();释放所有资源
        try {
            doUse();
        }finally {
            semaphore.release();        //用于释放资源
        }
    }
    protected void doUse() throws InterruptedException{
        Log.println("开始:used="+(permits-semaphore.availablePermits()));
        Thread.sleep(random.nextInt(500));
        Log.println("END:used="+(permits-semaphore.availablePermits()));
    }
}
class Log{
    public static void println(String s){
        System.out.println(Thread.currentThread().getName()+":"+s);
    }
}
//使用资源的线程
class UserThread extends Thread{
    private final static Random random=new Random(26535);
    private final BoundedResource resource;
    public UserThread( BoundedResource resource){
        this.resource=resource;
    }
    @Override
    public void run() {
        try {
            while (true){
                resource.use();
                Thread.sleep(random.nextInt(3000));
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Main{
    public static void main(String[] args) {
        //设置3个资源
        BoundedResource resource=new BoundedResource(3);
        //10个线程使用资源
        for(int i=0;i<10;i++){
            new UserThread(resource).start();
        }
    }
}