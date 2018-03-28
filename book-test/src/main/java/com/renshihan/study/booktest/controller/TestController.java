package com.renshihan.study.booktest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String test1(@RequestParam(value = "username")String username, @RequestParam(value = "password") String password, Model model) {
        log.info("传入的参数username:{},password:{}", username, password);
        model.addAttribute("name", username);
        return "hello";
    }
}
