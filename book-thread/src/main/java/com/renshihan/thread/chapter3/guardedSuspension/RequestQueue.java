/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @Copyright ©2019 Suixingpay. All rights reserved.
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.renshihan.thread.chapter3.guardedSuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ${TODO} 写点注释吧
 * @author: ren_sh[ren_sh@suixingpay.com]
 * @date: 2019/2/27 17:56
 * @version: V1.0
 * @review: ren_sh[ren_sh@suixingpay.com]/2019/2/27 17:56
 */
public class RequestQueue {

    private final Queue<Request> queue= new LinkedList<>();

    public synchronized Request getRequest(){
        while (queue.peek()==null){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        return queue.remove();
    }

    public synchronized void putRequest(Request request){
        queue.offer(request);
        notifyAll();
    }


}