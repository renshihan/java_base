package com.renshihan.study.book.netty.redis.controller;


import com.renshihan.study.book.netty.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by xiaour on 2017/4/19.
 */
@RestController
@RequestMapping(value="/test")
@Slf4j
public class TestCtrl {
	
	@Autowired
	private RedisService redisService;


    @RequestMapping(value="/index")
    public String index(){
        return "hello world";
    }
    
    /**
     * 向redis存储值
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    @RequestMapping("/set")
    public String set(String key, String value) throws Exception {
        log.info("进入参数{},{}",key,value);
        redisService.set(key, value);
        return "success";  
    }  
    
    /**
     * 获取redis中的值
     * @param key
     * @return
     */
    @RequestMapping("/get")
    public String get(String key){
        try {
			return redisService.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";  
    }
    

}
