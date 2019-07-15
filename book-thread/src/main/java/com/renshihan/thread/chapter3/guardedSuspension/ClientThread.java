/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter3.guardedSuspension;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 表示发送请求得客户端
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/2/27 17:56
 */
@Slf4j
public class ClientThread extends Thread{
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue,String name,long seed) {
        super(name);
        this.requestQueue=requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            Request request=new Request("No."+i);
            requestQueue.putRequest(request);
            log.info("{} - requests- {}",Thread.currentThread().getName(),request);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}