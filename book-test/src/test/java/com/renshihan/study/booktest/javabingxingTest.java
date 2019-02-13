/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 11:18
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest;

import com.renshihan.study.booktest.javabingxing.chapter2.MatrixGenerator;
import com.renshihan.study.booktest.javabingxing.chapter2.ParallelGroupMutiplier;
import com.renshihan.study.booktest.javabingxing.chapter2.SerialMultiplier;
import org.junit.Test;

import java.util.Date;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 11:18
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/1/31 11:18
 */
public class javabingxingTest {
    @Test
    public void test1(){
        double matrix1[][]= MatrixGenerator.generate(2000,2000);
        double matrix2[][]=MatrixGenerator.generate(2000,2000);
        double resultSeria[][]=new double[matrix1.length][matrix2[0].length];
        Date start=new Date();
        SerialMultiplier.multiply(matrix1,matrix2,resultSeria);
        Date end=new Date();
        System.out.printf("串行执行：%d%n",(end.getTime()-start.getTime()));
    }
    @Test
    public void test2(){
        double matrix1[][]= MatrixGenerator.generate(2000,2000);
        double matrix2[][]=MatrixGenerator.generate(2000,2000);
        double resultSeria[][]=new double[matrix1.length][matrix2[0].length];
        Date start=new Date();
        SerialMultiplier.multiply(matrix1,matrix2,resultSeria);
        Date end=new Date();
        System.out.printf("串行执行：%d%n",(end.getTime()-start.getTime()));
    }
    @Test
    public void test3(){
        double matrix1[][]= MatrixGenerator.generate(2000,2000);
        double matrix2[][]=MatrixGenerator.generate(2000,2000);
        double resultSeria[][]=new double[matrix1.length][matrix2[0].length];
        Date start=new Date();
        SerialMultiplier.multiply(matrix1,matrix2,resultSeria);
        Date end=new Date();
        System.out.printf("串行执行：%d%n",(end.getTime()-start.getTime()));
    }
    @Test
    public void test4(){
        double matrix1[][]= MatrixGenerator.generate(2000,2000);
        double matrix2[][]=MatrixGenerator.generate(2000,2000);
        double resultSeria[][]=new double[matrix1.length][matrix2[0].length];
        Date start=new Date();
        ParallelGroupMutiplier.multiply(matrix1,matrix2,resultSeria);
        Date end=new Date();
        System.out.printf("并行：%d%n",(end.getTime()-start.getTime()));
    }
}