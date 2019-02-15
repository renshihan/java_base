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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 委托者
 * 发送工作的请求类
 */
@Slf4j
public class ClientThread extends Thread{
    private final Channel channel;
    private static final Random random=new Random();

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request=new Request(getName(),i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            log.error("线程中断异常",e);
        }
    }
}