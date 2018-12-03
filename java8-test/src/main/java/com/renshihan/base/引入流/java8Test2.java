/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/11/23 19:55
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.引入流;

import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * 6章节  --用流收集数据 Collect收集器
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/11/23 19:55
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/11/23 19:55
 */
public class java8Test2 {
   private List<Transaction> transactions= Arrays.asList(
            new Transaction("小兵",18),
            new Transaction("效力",19),
            new Transaction("abc",122),
            new Transaction("abc",119),
            new Transaction("小兵",139),
            new Transaction("小王",16),
            new Transaction("小张",1119)
    );
    @Data
    class Transaction{
        private String name;
        private int amount;
        public Transaction(String name) {
            this.name = name;
        }

        public Transaction(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }
    }
    /**
     * 分组
     * 多级分组
     * */
    @Test
    public void test1(){

        transactions.stream().collect(groupingBy(Transaction::getName));
        System.out.println("人数："+transactions.stream().collect(counting()));
        System.out.println("人数："+transactions.stream().count());






    }

    public void test2(){
//        IntStream intStream=IntStream.builder().add(1).add(10).add(16).add(17).add(2).add(-2).add(-8).build();

        List<Integer> list=Arrays.asList(1,23,3,5,6,4,3,4,6,7,8,854,3,4,6,7,8,9,0,12);

    }

    @Test
    public void test3(){
        //6.2.1查找流中的最大值和最小值

        Comparator<Transaction> transactionComparator=Comparator.comparingInt(Transaction::getAmount);
        System.out.println("工资最大："+transactions.stream().collect(maxBy(transactionComparator)));
    }

    @Test
    public void test4(){
        //6.2.2汇总

        int totalAmount=transactions.stream().collect(summingInt(Transaction::getAmount));
        System.out.println("总工资---"+totalAmount);
        //推荐理由---intStream避免我们自动拆箱
        System.out.println("总工资（推荐）---"+transactions.stream().mapToInt(Transaction::getAmount).sum());
        //计算平均值
        System.out.println("平均工资----"+ transactions.stream().collect(averagingInt(Transaction::getAmount)));

        //

        System.out.println("一次得到全部信息---"+transactions.stream().collect(summarizingInt(Transaction::getAmount)));
    }

    @Test
    public void test5(){
        //6.2.3 链接字符串

        System.out.println("连接字符串---"+transactions.stream().map(Transaction::getName).collect(joining(" ")));


    }
    @Test
    public void test6(){
        //用reducing链接字符串
        String names=transactions.stream().collect(reducing("",Transaction::getName,(s1,s2)->s1+s2));
        //从可读性和性能方面考虑，始终建议使用joining
        String names2=transactions.stream().map(Transaction::getName).collect(joining());
        System.out.println(names);
    }

    @Test
    public void test7(){
        //6.3分组

    }

}