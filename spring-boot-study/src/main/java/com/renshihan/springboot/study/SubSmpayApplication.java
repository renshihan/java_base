package com.renshihan.springboot.study;

import com.ace.cache.EnableAceCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableAceCache
public class SubSmpayApplication {
	public static void main(String[] args) {
		SpringApplication.run(SubSmpayApplication.class, args);
	}
}
