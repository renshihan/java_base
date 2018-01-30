package com.renshihan.javabase.thread.callback;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.util.concurrent.CompletableFuture;


/**
 * Created by admin on 2017/11/1.
 * 通过回调机制实现，即首先发出网络请求，当网络返回时回调相关方法，如
 * httpAsyncClien使用基于NIO的异步I/O模型实现，它实现了Reactor模式，摈弃阻塞I/O模型one thread per connection
 * 采用线程池分发时间通知，从而有效支撑大量并发链接、这种机制并不能提升性能，而是为了支撑大量并发链接或者提高吞吐量
 */
public class AsyncTest {
    public static HttpAsyncClient httpAsyncClient;
    public static CompletableFuture<String> getHttpData(String url){
        CompletableFuture asyncFuture = new CompletableFuture();
        HttpAsyncRequestProducer producer= HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer consumer=new BasicAsyncResponseConsumer();

        FutureCallback callback=new FutureCallback<HttpResponse>(){
            public void completed(HttpResponse httpResponse) {
                asyncFuture.complete(httpResponse);
            }

            public void failed(Exception e) {
                asyncFuture.completeExceptionally(e);
            }

            public void cancelled() {
                asyncFuture.cancel(true);
            }
        };
        httpAsyncClient.execute(producer,consumer,callback);
        return asyncFuture;
    }

    public static void main(String[] args) throws Exception{
        CompletableFuture<String> future=AsyncTest.getHttpData("http://www.jd.com");
        String result=future.get();
        System.out.println("---"+result);
    }
}
