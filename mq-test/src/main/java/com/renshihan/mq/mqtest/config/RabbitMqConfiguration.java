/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 12:05
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.mq.mqtest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * ${TODO} 写点注释吧
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2018/12/3 12:05
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2018/12/3 12:05
 */
@Configuration
public class RabbitMqConfiguration {
    public final static String QUEUE_NAME = "FIN.Antifraud_interceptNotifyQueue.n010";
    // 创建队列
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("FIN.Antifraud_interceptNotifyExchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("FIN.Antifraud_interceptNotifyQueue.n010");
    }

}