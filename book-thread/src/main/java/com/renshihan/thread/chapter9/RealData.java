/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/15 14:56
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter9;

import lombok.extern.slf4j.Slf4j;

/**
 * 表示实际的类，构造函数的处理会花费很多时间
 */
@Slf4j
public class RealData implements Data {
    private final String content;

    public RealData(int count, char c) {
        log.info("real-----初始化:count={},c={}--------开始", count, c);
        char[] buffer = new char[count];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.info("realData对象初始化线程中断异常", e);
            }
        }
        log.info("real-----初始化:count={},c={}--------结束", count, c);
        this.content = new String(buffer);
    }

    @Override
    public String getContent() {
        return content;
    }
}