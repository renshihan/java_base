package com.renshihan.study.book.netty.redis.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/22 18:20
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

}
