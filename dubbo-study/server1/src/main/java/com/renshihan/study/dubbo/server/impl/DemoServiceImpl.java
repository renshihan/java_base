package com.renshihan.study.dubbo.server.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.renshihan.common.rpc.DemoService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renshihan@winchannel.net
 * @date 2018/6/6 16:23
 */
@Service(version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}")
@Slf4j
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot)";
    }
}
