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
 * 表示RealData的“提货单”的类，其他线程会创建RealData实例
 */
@Slf4j
public class FutureData implements Data {

    private RealData realData = null;

    private boolean ready = false;


    public synchronized void setRealData(RealData realData) {
        if (ready) {
            //如果已经准备好，就进入下一个环节
            return;
        }
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        //如果未准备好=====还准备
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("线程中断异常", e);
            }
        }
        return realData.getContent();
    }

}