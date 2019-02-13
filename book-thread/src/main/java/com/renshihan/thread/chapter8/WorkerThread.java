/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/13 14:08
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter8;

/**
 * 表示工人线程的类
 * 工人线程会进行工作。“进行工作”这个处理对应示例程序中的以下处理。
 */
public class WorkerThread extends Thread{
    private final Channel channel;
    public WorkerThread(String name,Channel channel){
        super(name);
        this.channel=channel;
    }

    @Override
    public void run() {
        /* 工人线程一旦启动后，就会一直工作。也就是说，它会反复执行“获取一个新的Reuqest的实例”，
        然后调用它的execute方法处理。------------无需启动新的线程。
         */
        while (true){
            Request request=channel.takeRequset();
            request.execute();
        }
    }
}