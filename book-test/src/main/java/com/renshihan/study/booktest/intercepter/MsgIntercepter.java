package com.renshihan.study.booktest.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/28 12:11
 * 打印传入request信息的过滤器
 */
@Slf4j
public class MsgIntercepter extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入请求类型:{}",request.getMethod());
        log.info("访问路径:{}",request.getRequestURI());
        Enumeration<String> enumeration=request.getHeaderNames();
        while( enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            log.info("请求头信息 name:{},value:{}",name,request.getHeader(name));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("一次请求结束#################################################");
    }
}
