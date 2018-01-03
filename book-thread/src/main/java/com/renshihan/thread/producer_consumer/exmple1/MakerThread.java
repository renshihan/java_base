package com.renshihan.thread.producer_consumer.exmple1;


import com.renshihan.thread.producer_consumer.exmple1.Table;

import java.util.Random;

/**
 * Created by admin on 2017/12/30.
 * 糕点师
 */
public class MakerThread extends Thread{
    private final Random random;
    private final Table table;
    private static int id=0;
    public MakerThread(String name, Table table,long seed){
        super(name);
        this.table=table;
        this.random=new Random(seed);
    }
    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(random.nextInt(1000));
                String cake="{蛋糕编号: " + next() +" by " +getName()+"}";
                table.put(cake);
            }
        }catch (InterruptedException e){

        }
    }
    private static synchronized int next(){
        return id++;
    }
}
