/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 15:45
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3;

import java.util.concurrent.FutureTask;

/**
 * 执行器任务
 * 向执行器提交Runnable对象时，它并不会直接执行该对象，而是
 * 创建新的对象，即FutureTask类的一个实例，而且这项任务由执行器的工作
 * 线程执行。
 */
public class ServerTask<V> extends FutureTask<V> implements Comparable<ServerTask<V>> {
    @Override
    public int compareTo(ServerTask<V> o) {
        return 0;
    }

    public ServerTask(Runnable runnable, V result) {
        super(runnable, result);
    }
}