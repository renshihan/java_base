/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/15 14:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter9;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 向Host发出请求并获取数据的类
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Main开始执行....");
        Host host=new Host();
        Data data1=host.request(12,'a');
        Data data2=host.request(12,'b');
        Data data3=host.request(12,'c');
        log.info("Main others Job 开始执行....");
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            log.error("线程中断异常",e);
        }
        log.info("Main others Job 结束执行....");
        log.info("data1={}",data1.getContent());
        log.info("data2={}",data2.getContent());
        log.info("data3={}",data3.getContent());

        Map<String,String> a=new HashMap<>(0);

        log.info("---->>>>>{}",a.isEmpty());
    }
}