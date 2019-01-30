/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/30 18:23
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter2;

/**
 * 串行版本
 */
public class SerialMultiplier {
    public static void multiply(double[][] matrix1,double[][] matrix2,double[][] result){
        int rows1=matrix1.length;
        int columns1=matrix1[0].length;
        int columns2=matrix2[0].length;
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                result[i][j]=0;
                for (int k = 0; k < columns1; k++) {
//                    System.out.println(String.format("(%s,%s),(%s,%s)*(%s,%s)",i,j,i,k,k,j));
                    result[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }
        }

    }
}