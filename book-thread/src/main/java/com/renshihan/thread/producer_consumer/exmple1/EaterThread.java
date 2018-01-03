package com.renshihan.thread.producer_consumer.exmple1;

import com.renshihan.thread.producer_consumer.exmple1.Table;

import java.util.Random;

/**
 * Created by admin on 2017/12/30.
 * 表示客人的类
 */
public class EaterThread extends Thread{
    private final Random random;
    private final Table table;

    public EaterThread(String name,Table table,long seed) {
        super(name);
        this.random = new Random(seed);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true){
                String cake=table.take();   //拿蛋糕
                Thread.sleep(random.nextInt(1000));
            }
        }catch (InterruptedException e){

        }
    }
}
