package com.renshihan.study.archaius.study.controller;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.apache.commons.configuration.AbstractConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArchaiusController {
    Logger logger= LoggerFactory.getLogger(ArchaiusController.class);
    private static final DynamicStringProperty MY_PROP=DynamicPropertyFactory.getInstance().getStringProperty("channel.test1","没找到");
    @Autowired
    private AbstractConfiguration concurrentCompositeConfiguration;
    @RequestMapping(value = "/hellow")
    public String hello (@RequestParam String select){

        logger.info("番薯-----"+select);
        DynamicPropertyFactory.initWithConfigurationSource(concurrentCompositeConfiguration);
        return MY_PROP.get();
    }
}
