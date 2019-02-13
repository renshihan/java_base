/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 10:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

/**
 * 第二个并发版本：每行一个线程
 */
public class RowMultiplierTask implements Runnable{
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;

    public RowMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }

    /**
     * run（）方法有两个循环，第一个循环将处理待计算结果矩阵row中的所有元素，而第二个循环将计算每个元素的结果值
     */
    @Override
    public void run() {
        for (int i = 0; i <matrix2[0].length; i++) {
            result[row][i]=0;
            for (int j = 0; j < matrix1[row].length; j++) {
                result[row][i]+=matrix1[row][j]*matrix2[j][i];
            }
        }
    }
}