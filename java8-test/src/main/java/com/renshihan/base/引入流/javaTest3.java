/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/4 20:06
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.引入流;

import com.renshihan.base.forkjoin.ForkJoinSumCalculator;
import com.renshihan.base.forkjoin.ForkJoinTest;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 并行流
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/4 20:06
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/4 20:06
 */
public class javaTest3 {
    @Test
    public void test1(){
        System.out.println(sequentialSum(100L));
    }

    /**
     * 顺序流执行结果
     * @param n
     * @return
     */
    public static long sequentialSum(long n ){
        return Stream
                //生成自然数无限流
                .iterate(1L,i->i+1)
                //限制到前n个数
                .limit(n)
                //对所有数字求和来归纳流
                .reduce(0L,Long::sum);

        //sequential将并行流改为顺序流

    }

    /**
     * LongStream.rangeClosed与iterate比较有两个优点：
     * 直接产生原始类型得数字，没有拆装箱得开销。
     * 会生成数字范围，很容易拆分为独立得小块。
     * @param n
     * @return
     */
    public static long rangedSum(long n){
        return LongStream.rangeClosed(1,n).reduce(0L,Long::sum);
    }

    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1,n).parallel().reduce(0L,Long::sum);
    }
    /**
     * 并行执行
     * @param n
     * @return
     */
    public static long parallelSum(long n ){
        return Stream
                //生成自然数无限流
                .iterate(1L,i->i+1)
                //限制到前n个数
                .limit(n)
                //将流转换为并行流
                .parallel()
                //对所有数字求和来归纳流
                .reduce(0L,Long::sum);

        //sequential将并行流改为顺序流

    }
    /**
     * 传统得迭代
     * */
    public static long iterativeSum(long n){
        long result=0;
        for (long i = 1L; i <= n; i++) {
            result+=i;
        }

        return result;
    }

    /**
     * forkjoin
     * @param n
     * @return
     */
    public static long forkJoinSum(long n){
        long[] numbers=LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task=new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }
    /**
     * 配置并行流使用得线程池
     * 默认使用了ForkJoinPool---默认得线程数量就是处理器数量
     * */
    @Test
    public void test2(){

        int size=Runtime.getRuntime().availableProcessors();
        System.out.println("默认forkJoinPool线程池大小（处理器数量）:"+size);
//      可通过系统属性 java.util.concurrent.ForkJoinPool.common.parallelism来改变线程池大小.
        //全局设置，会影响代码中所有得并行流，目前还无法专门为某个并行流指定这个值，一般默认即好
//      System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","12");

    }

    /**
     * 测量流性能
     * 问题：并行版本比顺序执行版本要慢得多，如何解释?
     * （1）iterate生成得是装箱得对象，必须拆成数字才能求和。
     * （2）我们很难b把iterate分成多个独立块来并行执行
     * 表明：使用正确得数据结构然后使其并行工作能够保证最佳得功能。
     * 使用并行：保证在内核中并行执行工作得时间比在内核之间传递数据得时间更长。
     * */
    @Test
    public void test3(){
//      顺序流执行|Sequential sum done in:127 mesecs
//        System.out.println("顺序流执行|Sequential sum done in:" + measureSumPerf(javaTest3::sequentialSum,10_000_000) + " mesecs");
////        Iterative sum done in:2 mesecs
//        System.out.println("传统for执行|Iterative sum done in:" + measureSumPerf(javaTest3::iterativeSum,10_000_000) + " mesecs");
////      并行执行|parallel sum done in:148 mesecs
//        System.out.println("并行执行|parallel sum done in:" + measureSumPerf(javaTest3::parallelSum,10_000_000) + " mesecs");
//        System.out.println("------------------------优化---------------------------");
//        //顺序流|ranged sum done in:3 mesecs
//        System.out.println("顺序流|ranged sum done in:" + measureSumPerf(javaTest3::rangedSum,10_000_000) + " mesecs");
////        并行执行|ranged sum done in:1 mesecs
//        System.out.println("并行执行|ranged sum done in:" + measureSumPerf(javaTest3::parallelRangedSum,10_000_000) + " mesecs");
        System.out.println("--------------------------其他------------------------------");
        System.out.println("并行执行|forkjoin sum done in:" + measureSumPerf(javaTest3::forkJoinSum,10_000_000) + " mesecs");
    }

    /**
     * 测量对前n个自然数求和得函数得性能
     * @param adder
     * @param n
     * @return
     */
    public long measureSumPerf(Function<Long,Long> adder,long n){
        long fastest=Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start=System.nanoTime();
            long sum=adder.apply(n);
            //记录每次执行得时间（以毫秒为单位）
            long duration=(System.nanoTime()-start)/1_000_000;
//           System.out.println("Result "+sum);
            if(duration<fastest) {
                //并返回最短一次执行得时间
                fastest = duration;
            }
        }
        return fastest;
    }



}