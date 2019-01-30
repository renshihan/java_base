/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:51
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:51
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/1/30 18:51
 */
public class IndividualMultiplierTask implements Runnable{
    private final double[][] result;
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;
    private final int column;

    public IndividualMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row, int column) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
        this.column = column;
    }

    @Override
    public void run() {
        result[row][column]=0;
        for (int i = 0; i < matrix1[row].length; i++) {
            result[row][column]+=matrix1[row][i]*matrix2[i][column];
        }
    }
}