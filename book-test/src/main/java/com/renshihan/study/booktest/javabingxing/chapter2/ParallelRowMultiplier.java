/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 10:21
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类创建计算矩阵所需的所有执行线程。
 */
public class ParallelRowMultiplier {
    public static void multiply(double[][] matrix1,double[][] matrix2,double[][] result){
        List<Thread> threads=new ArrayList<>();
        int rows1=matrix1.length;
        for (int i = 0; i < rows1; i++) {
            RowMultiplierTask task=new RowMultiplierTask(result,matrix1,matrix2,i);
            Thread thread=new Thread(task);
            thread.start();
            threads.add(thread);
            if(threads.size()%10==0){
                waitForThreads(threads);
            }
        }
    }
    private static void waitForThreads(List<Thread> threads){
        for (Thread thread : threads) {
            try {
                //主线程等待该线程结束
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}