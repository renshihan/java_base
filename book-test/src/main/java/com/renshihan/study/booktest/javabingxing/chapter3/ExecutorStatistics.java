/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 15:28
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 存储服务器计算每个用户在服务器执行的任务数量
 */
@Data
public class ExecutorStatistics {
    private AtomicLong executionTime=new AtomicLong(0L);
    private AtomicInteger numTasks=new AtomicInteger(0);
    public void addExecutionTime(Long time){
        executionTime.addAndGet(time);
    }
    public void addTask(){
        numTasks.incrementAndGet();
    }

    @Override
    public String toString() {
        return "ExecutorStatistics{" +
                "executionTime=" + getExecutionTime() +
                ", numTasks=" + getNumTasks() +
                '}';
    }
}