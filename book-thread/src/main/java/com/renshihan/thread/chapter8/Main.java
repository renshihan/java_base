/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/13 14:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter8;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
@Slf4j
public class Main {
    public static void main(String[] args) {

        Channel channel=new Channel(5);
        channel.startWorkders();
        new ClientThread("任时汉",channel).start();
        new ClientThread("姜钱",channel).start();
        new ClientThread("明星",channel).start();
        ClientThread clientThread=new ClientThread("aaaa",channel);
        log.info("-----------");
        SwingUtilities.invokeLater(clientThread);
        TimerTask timerTask=new TimerTask(){
            @Override
            public void run() {

            }
        };
    }
}