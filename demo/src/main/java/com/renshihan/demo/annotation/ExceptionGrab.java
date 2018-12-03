/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/10/9 17:44
 * @Copyright ©2018 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ${TODO} 写点注释吧
 * @author: xing_xin[xing_xin@suixingpay.com]
 * @date: 2018/10/9 17:44
 * @version: V1.0
 * @review: xing_xin[xing_xin@suixingpay.com]/2018/10/9 17:44
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionGrab {
    String[] exception() default {};
}