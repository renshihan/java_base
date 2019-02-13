/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/1/31 16:16
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.study.booktest.javabingxing.chapter3.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * 并发命令
 */
@Slf4j
@Data
public abstract class ConcurrentCommand extends Command implements Comparable<ConcurrentCommand>,Runnable{
    private String userName;
    private byte priority;
    private Socket socket;

    public ConcurrentCommand(Socket socket,String[] command) {
        super(command);
        this.socket = socket;
    }
    @Override
    public int compareTo(ConcurrentCommand o) {
        return Byte.compare(o.getPriority(),this.getPriority());
    }

    @Override
    public void run() {
        String message="执行任务:userName:"+userName+";Priority";
        log.info(message);
        String ret=execute();
    }


}