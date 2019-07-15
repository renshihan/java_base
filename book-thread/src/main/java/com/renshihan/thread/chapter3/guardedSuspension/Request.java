/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter3.guardedSuspension;

import lombok.Data;

/**
 * 表示一个请求的类
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/2/27 17:56
 */
@Data
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}