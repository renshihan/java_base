package com.renshihan.study.book.netty.client.echo.client;

import com.renshihan.study.book.netty.client.echo.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/22 13:56
 */

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建BootStrap
            Bootstrap bootstrap = new Bootstrap();    //指定
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);   //类似于NIO传输的channel工具类
            bootstrap.remoteAddress(new InetSocketAddress(host, port));  //设置服务器的InetSocketAddress地址
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            ChannelFuture channelFuture=bootstrap.connect().sync();         //连接到远程节点，阻塞等待直到连接完成
            channelFuture.channel().closeFuture().sync();                   //阻塞，直到channel关闭
        } finally {
            group.shutdownGracefully().sync();  //关闭线程池并释放所有资源
        }
    }

    public static void main(String[] args) {
        String host="localhost";
        int port=Integer.parseInt("8888");
        try {
            new EchoClient(host,port).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
