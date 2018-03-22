package com.renshihan.study.book.netty.server;

import com.renshihan.study.book.netty.server.echo.server.EchoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookNettyServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookNettyServerApplication.class, args);
	}
}
