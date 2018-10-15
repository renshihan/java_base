/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 11:44
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/10/15 11:44
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/10/15 11:44
 */
public class LambdaTest1 {
    public static void main(String[] args) {
//        (parameters) -> expression
        Sysout s=()-> System.out.println("hello");
        s.a();
        try {
            proccessFile((BufferedReader reader)->reader.readLine());   //读一行
            proccessFile((BufferedReader reader)->reader.readLine()+reader.readLine());   //读两行

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FunctionalInterface
    public interface Sysout{
        public void a();
    }
    //java7中带资源try语句
    //将行为封装好，传递行为
    public static String proccessFile(BufferedReaderProccessor proccessor)throws IOException{
        try (BufferedReader reader=new BufferedReader(new FileReader("aaa.txt"))){
            return proccessor.proccess(reader);
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProccessor{
        String proccess(BufferedReader bufferedReader)throws IOException;
    }
}