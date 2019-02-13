/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/13 14:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter8;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 工作请求的类
 */
@Slf4j
public class Request {
    /**
     * 表示发送请求的委托者的名称
     */
    private final String name;
    /**
     * 请求的编号
     */
    private final int number;
    private static final Random random=new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        log.info("name={},number={}...开始执行...",name,number);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            log.error("request线程中断异常",e);
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}