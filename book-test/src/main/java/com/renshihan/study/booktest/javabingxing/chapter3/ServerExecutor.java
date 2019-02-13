/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 15:50
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 执行器本身
 */
public class ServerExecutor extends ThreadPoolExecutor{
    private ConcurrentHashMap<Runnable,Date> startTimes;
    private ConcurrentHashMap<String,ExecutorStatistics> executionStatistics;
    //cpu数量
    private static int CORE_POOL_SIZE=Runtime.getRuntime().availableProcessors();
    private static int MAXIMUM_POOL_SIZE=Runtime.getRuntime().availableProcessors();
    private static long KEEP_ALIVE_TIME=10;
    private static RejectedTaskController REJECTED_TASK_CONTROLLER=new RejectedTaskController();

    public ServerExecutor(){
        super(CORE_POOL_SIZE,MAXIMUM_POOL_SIZE,KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
//                存储那些将在执行器中执行的任务,该类更具compareTo()方法的执行结果对 元素排序。（此中存储的元素必须实现Comparable接口）
                new PriorityBlockingQueue<>(),
                REJECTED_TASK_CONTROLLER);
        startTimes=new ConcurrentHashMap<>();
        executionStatistics=new ConcurrentHashMap<>();
    }

    /**
     * 在每个任务执行之前执行
     * @param t 执行该任务的线程
     * @param r ServerTask对象
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        //存放执行开始日期
        startTimes.put(r,new Date());
    }

    /**
     * 每个任务执行完毕后执行
     * @param r 已执行的ServerTask对象
     * @param t Throwable对象 只有抛出了异常才会有值
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {

        super.afterExecute(r, t);
        ServerTask<?> task=(ServerTask<?>) r;

    }

    /**
     * 该方法执行后会转换发送给执行器的Runnable对象，使用执行器待执行的FutureTask实例的submit
     * @param runnable
     * @param value
     * @param <T>
     * @return
     */
    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new ServerTask<T>(runnable,null);
    }
}