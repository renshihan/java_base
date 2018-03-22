package com.renshihan.study.book.netty.server.echo.server;

import com.renshihan.study.book.netty.server.echo.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/21 16:45
 */
@Slf4j
public class EchoServer {
    @Value("${echo.netty.server.port}")
    private Integer port;

    public EchoServer(Integer port) {
        this.port=port;
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //1.创建eventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //2..创建server-bootStrap
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)//3..指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) //4.使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer() {
                        //5.添加一个EchoServerHander到子ChannelPipeline

                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            //pipeline-----管道
                            //当一个新的连接被接收时，一个新的Channel被创建，ChannelInitializer会将一个serverHandler实例添加到channel的channelPipeline中，从而收到该通道有关的入站通知
                            channel.pipeline().addLast(serverHandler);
                        }

                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();      //6.异步绑定服务器，调用sync（）

            channelFuture.channel().closeFuture().sync();           //7.获取阻塞当前线程知道它完成。


        } finally {
            group.shutdownGracefully().sync();          //8.关闭eventLoopGroup，释放所有资源
        }
    }

    public static void main(String[] args) {
        try {
            log.info("echoServer开始启动...");
            new EchoServer(8888).start();
        }catch (Exception e){
            log.info("echoServer启动异常",e);
        }
    }
}
