/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/15 14:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter9;

import lombok.extern.slf4j.Slf4j;

/**
 * 向请求返回FutureData类
 */
@Slf4j
public class Host {
    /**
     * @param count
     * @param c
     * @return
     */
    public Data request(final int count, final char c) {
        log.info("请求---count={},c={}-----开始", count, c);
        //创建FutureData的实例
        final FutureData futureData = new FutureData();

        //启动一个线程，用于创建RealData的实例
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(count, c);
                futureData.setRealData(realData);
            }
        }.start();
        log.info("请求---count={},c={}-----结束", count, c);
        //返回future的实例
        return futureData;
    }
}