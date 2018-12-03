/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 15:04
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.mq.mqtest.controller;

import com.renshihan.mq.mqtest.service.SendMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 15:04
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/3 15:04
 */
@Controller
@Slf4j
public class RabbitMqController {
    @Autowired
    private SendMqService sendMqService;
    @RequestMapping("/send")
    public String sendMsg(){
        sendMqService.send();
        log.info("发送mq完成...");
        return "success";
    }


}