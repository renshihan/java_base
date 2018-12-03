/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/10/9 17:52
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * ${TODO} 写点注释吧
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/10/9 17:52
 * @version: V1.0
 * @review: xing_xin[xing_xin@suixingpay.com]/2018/10/9 17:52
 * 异常抓取配置类
 */
public class ExceptionGrabConfiguration {
    private static Map<String,Exception> classContainer=new HashMap<>();
    @ConfigurationProperties("")
    public void init(Exception e){

    }
}