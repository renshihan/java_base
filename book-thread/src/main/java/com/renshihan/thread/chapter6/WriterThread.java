package com.renshihan.thread.chapter6;

import java.util.Random;

/**
 * Created by admin on 2018/11/22.
 * 是对data实例执行写入操作的线程。构造函数的参数filler是一个字符串
 * 程序会逐个取出该字符串中的字符，并write到data的实例中。每写入一次
 * 线程就会在0~3000毫秒这个范围内随机sleep一段时间。此外，nextchar方法用于获取下一次
 * 应该写入的字符。
 *
 * 该类中没有与互斥处理相关的代码
 */
public class WriterThread  extends Thread{
    private static final Random random=new Random();
    private final Data data;
    private final String filler;
    private int index=0;
    public WriterThread(Data data,String filler){
        this.data=data;
        this.filler=filler;
    }
    @Override
    public void run(){
        try {
            while (true){
                char c=nextchar();
                data.write(c);
                Thread.sleep(random.nextInt(3000));
            }
        }catch (InterruptedException e){

        }
    }
    /**
     *
     * */
    private char nextchar(){
        char c=filler.charAt(index);
        index++;
        if(index>=filler.length()){
            index=0;
        }
        return c;
    }
}
