package com.renshihan.javabase.thread.future;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/10/11.
 * 线程池配合Future实现，但是阻塞主请求线程，高并发依然会造成线程数过多，CPU上下文切换。
 * 通过Future可以并发发出N个请求，然后等待最慢的一个返回，总响应时间为最慢的一个请求返回的用时。如下请求如果并发，则响应可以在30ms后返回
 */
public class Test {
    final static ExecutorService executor= Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        RpcService rpcService=new RpcService();
        HttpService httpService=new HttpService();
        Future<Map<String,String>> future1=null;
        Future<Integer> future2=null;
        try {
            System.out.println("=====开始测试=====");
            future1=executor.submit(() -> rpcService.getRpcResult());
            System.out.println("--------112-------");
            future2=executor.submit(()-> httpService.getHttpResult());
            System.out.println("--------2222----");
            //耗时为10ms
            Map<String,String> result1=future1.get(30000, TimeUnit.MILLISECONDS);
            System.out.println("----------分割点------------");
            // 耗时20ms
            Integer result2=future2.get(30000,TimeUnit.MILLISECONDS);
            //总耗时30ms
            System.out.println("=====结束测试=====");
        }catch (Exception e){
            if (future1!=null){
                future1.cancel(true);
            }
            if(future2!=null){
                future2.cancel(true);
            }
            throw new RuntimeException(e);
        }
        System.out.println("---------程序完成---------");
    }
    static class RpcService{
        Map<String,String> getRpcResult() throws Exception{
            System.out.println("!!!!!!!RpcService开始!!!!!!!!");
            //调用远程方法（远程方法耗时约10ms，可以使用Thread.sleep模拟）
            Thread.sleep(5000);       //10ms
            System.out.println("!!!!!!!RpcService结束!!!!!!!!");
            return new HashMap<>();
        }
    }
    static class HttpService{
        Integer getHttpResult()throws Exception{
            System.out.println("*******getHttpResult开始*******");
            //调用远程方法（远程方法耗时约20ms）
            Thread.sleep(10000);
            System.out.println("*******getHttpResult结束*******");
            return 0;
        }
    }

}
