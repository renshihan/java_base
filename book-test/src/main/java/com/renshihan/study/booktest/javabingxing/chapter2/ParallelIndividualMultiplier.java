/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:57
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:57
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/1/30 18:57
 */
public class ParallelIndividualMultiplier {
    public static void multiply(double[][] matrix1,double[][] matrix2,double[][] result){
        List<Thread> threads=new ArrayList<>();
        int rows1=matrix1.length;
        int rows2=matrix2.length;
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < rows2; j++) {
                IndividualMultiplierTask task=new IndividualMultiplierTask(result,matrix1,matrix2,i,j);
                Thread thread=new Thread(task);
                thread.start();
                threads.add(thread);
                if(threads.size()%10 ==0){
                    waitForThreads(threads);
                }
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