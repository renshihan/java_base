package com.renshihan.study.book.netty.client.echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/22 12:45
 */
@Slf4j
@ChannelHandler.Sharable  //标记该类的实例可以被多个channel共享
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
    * @author: renshihan@winchannel.net
    * @Description: 当从服务器接收到一条消息被调用
    * @Date 2018/3/22 13:30
    * @Param 
    * @return 
    */
    //每当服务器接收数据的时候都会调用，所以可能由服务器发送的数据会被分块接收
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        log.info("接收到服务器返回的信息-----{}",byteBuf.toString(CharsetUtil.UTF_8));
    }
    /**
    * @author: renshihan@winchannel.net
    * @Description: 在到服务器的连接已经建立之后将被调用
    * @Date 2018/3/22 13:31
    * @Param 
    * @return 
    */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当被通知Channel是活跃的时候，发送一条信息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }
    /**
    * @author: renshihan@winchannel.net
    * @Description: 在处理过程中引发异常时被调用
    * @Date 2018/3/22 13:32
    * @Param 
    * @return 
    */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //发生异常时，记录错误并关闭channel（终止到服务器的连接）
        log.info("客户端触发异常----{}",cause);
        ctx.close();
    }

}
