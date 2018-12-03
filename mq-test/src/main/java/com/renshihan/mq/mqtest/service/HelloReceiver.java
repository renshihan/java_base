/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 15:09
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.mq.mqtest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 15:09
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/3 15:09
 */
@Component
@Slf4j
public class HelloReceiver {

    @RabbitListener(queues = "FIN.Antifraud_interceptNotifyQueue.n010")
    @RabbitHandler
    public void process(Object json) {
        log.info("Receiver  : " + json);
    }

}