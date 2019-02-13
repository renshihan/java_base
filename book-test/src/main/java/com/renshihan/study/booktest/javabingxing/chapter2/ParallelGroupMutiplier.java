/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 10:40
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 将闯进啊线程计算结构矩阵
 */
public class ParallelGroupMutiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int rows1 = matrix1.length;
        //虚拟机得可用cpu数量
        int numThreads = Runtime.getRuntime().availableProcessors();
        int startIndex, endIndex, step;
        step = rows1 / numThreads;
        startIndex = 0;
        endIndex = step;
        for (int i = 0; i < numThreads; i++) {
            GroupMultiplierTask task =
                    new GroupMultiplierTask(result, matrix1, matrix2, startIndex, endIndex);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            startIndex = endIndex;
            endIndex = i == numThreads - 2 ? rows1 : endIndex + step;
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}