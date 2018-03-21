package com.renshihan.study.book.netty.server;

import com.renshihan.study.book.netty.server.echo.server.EchoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookNettyServerApplication {
	@Autowired
	private EchoServer echoServer;
	public static void main(String[] args) {
		SpringApplication.run(BookNettyServerApplication.class, args);

	}
}
