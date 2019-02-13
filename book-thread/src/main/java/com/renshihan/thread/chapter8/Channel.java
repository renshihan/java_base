/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/13 14:07
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter8;

import lombok.extern.slf4j.Slf4j;

/**
 * 通讯线路
 * 负责传递工作请求以及保存工人线程的类
 */
@Slf4j
public class Channel {
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    //下次putRequest的位置
    private int tail;
    /**
     * 下次takeRequest的位置
     */
    private int head;
    /**
     * request的数量
     */
    private int count;

    private final WorkerThread[] threadPool;

    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;

        threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    public void startWorkders() {
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    public synchronized void putRequest(Request request){
        //当总request数量大于队列存放的等待执行的request数量时
        while (count>=requestQueue.length){
            try {
                wait();
            } catch (InterruptedException e) {
                log.info("Request放入channel队列异常",e);
            }
        }
        requestQueue[tail]=request;
        tail = (tail+1) % requestQueue.length;
        count++;
        notifyAll();

    }

    public synchronized Request takeRequset() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("takeRequest线程异常", e);
            }
        }
        Request request = requestQueue[head];

        head = (head + 1) % requestQueue.length;

        count--;

        notifyAll();

        return request;
    }

}