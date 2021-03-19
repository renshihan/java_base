/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/11/22 19:21
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.常规;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/11/22 19:21
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/11/22 19:21
 */
@Slf4j
public class java8Test {

    @Test
    public void test19(){
        for (int i = 0; i < 50; i++) {

            String randNum = new DecimalFormat("000").format((int)(Math.random()*100));
            log.info("产生得随机数--->{}",randNum);
        }
    }


    @Test
    public void test20(){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < 50; i++) {
            stringBuffer.append("---,--").append(",");
        }
        System.out.println(stringBuffer.toString());
        System.out.println(stringBuffer.substring(0,stringBuffer.length()-1));
    }

    @Test
    public void test21(){
        List<String> a=new ArrayList<>();
        for (int i = 0; i <3; i++) {
            a.add(i+"---");
        }
        averageAssign(a,5).stream().forEach(ab->{
            System.out.println("-----{}---"+ab.size());
        });
    }
    @Test
    public void test22(){
        for (int i = 8; i > 0; i--) {
            int randNum=(int)(Math.random()*100);
            if(randNum % 5 ==0){
                System.out.println("----命中throw---"+randNum);
            }else {
                System.out.println("----未命中------"+randNum);
            }
        }
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source,int n){
        List<List<T>> result=new ArrayList<List<T>>();
        int remaider=source.size()%n;  //(先计算出余数)
        int number=source.size()/n;  //然后是商
        int offset=0;//偏移量
        for(int i=0;i<n;i++){
            List<T> value=null;
            if(remaider>0){
                value=source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value=source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

    public static void main(String[] args)throws Exception {
//        String redeemStartTime="00:00:00";
//        String redeemEndTime="01:00:00";
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//
//        System.out.println(formatter.parse(redeemStartTime).getTime());
//        System.out.println(formatter.parse(redeemEndTime).getTime());

        Long endTIme=1611220275496L;


    }


}