package com.renshihan.study.booktest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/28 11:08
 */
@Controller
@Slf4j
@RequestMapping(value = "/test")
public class TestController {
    @RequestMapping(value = "/pythonT")
//    @ResponseBody
    public String test1(@RequestParam(value = "username")String username, @RequestParam(value = "password") String password, Model model, HttpServletRequest request) {
         //查看是否cookie有值
        if(null!=request.getCookies()){
            List cookiesLits= Arrays.asList(request.getCookies());
            log.info(" 传入的cookies----{}",cookiesLits);

        }


        log.info("传入的参数username:{},password:{}", username, password);
        model.addAttribute("name", username);
        return "hello";
    }
    @RequestMapping(value = "/index")
    @ResponseBody
    public String test1(String aaa) {
        return "test1";
    }
}
