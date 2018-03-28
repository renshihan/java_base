package com.renshihan.study.booktest;

import com.renshihan.study.booktest.intercepter.MsgIntercepter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Slf4j
public class BookTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTestApplication.class, args);
		log.info("测试book-test启动成功");
	}

	@Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new MsgIntercepter()).	//添加拦截器
					addPathPatterns("/test/*");	//添加拦截地址

		}
	}
}
