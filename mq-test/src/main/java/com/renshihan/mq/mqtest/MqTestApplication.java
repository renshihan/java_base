package com.renshihan.mq.mqtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@EnableRabbit
public class MqTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqTestApplication.class, args);
		log.info("测试mq发送者启动成功....");
	}
}
