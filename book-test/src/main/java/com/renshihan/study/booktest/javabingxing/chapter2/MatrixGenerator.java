/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:21
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

import java.util.Random;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:21
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/1/30 18:21
 */
public class MatrixGenerator {
    /**
     * 公共类
     * @param rows 行
     * @param columns 列
     * @return
     */
    public static double[][] generate(int rows,int columns){
        double[][] ret=new double[rows][columns];
        Random random=new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ret[i][j]=random.nextDouble()* 10;
            }
        }
        return ret;
    }
}