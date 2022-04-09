/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/5 16:41
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.base.forkjoin;


import java.util.concurrent.RecursiveTask;

/**
 * 用fork/join框架并行求和
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/5 16:41
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/5 16:41
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long>{
    //要求和的数组
    private final long[] numbers;
    //子任务处理的数组的起始和终止位置
    private final int start;
//
    private final int end;
    //不再将任务分解为子的系统的数组大小
    public static final long Threashold=10_000;
    @Override
    protected Long compute() {
        //该任务负责求和的部分和大小
        int length=end-start;
        if(length<=Threashold){
            return computeSequentailly();
        }
        ForkJoinSumCalculator leftTask=new ForkJoinSumCalculator(numbers,start,start+length/2);
        //利用forkjoin框架,异步执行新创建的子线程，为前半部分求和
        leftTask.fork();
        ForkJoinSumCalculator rightTask=new ForkJoinSumCalculator(numbers,start+length/2,end);
        //利用forkjoin框架,异步执行新创建的子线程
        //同步执行后半部分求和,有可能允许进一步递归划分

        Long rightResult=rightTask.compute();
        //读取第第一个任务，如果尚未完成就等待，
        Long leftResult=leftTask.join();

        return leftResult+rightResult;
    }


    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    private long computeSequentailly(){
        long sum=0;
        for (int i =start; i <end; i++) {
            sum+=numbers[i];
        }
        return sum;
    }
}