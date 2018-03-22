package com.renshihan.study.book.netty.server.echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author renshihan@winchannel.net
 * @date 2018/3/21 16:21
 * ChannelInboundHandlerAdapter每个方法重写以挂钩到事件生命周期上
 * 针对不同类型的事件来调用ChannelHandler
 *
 *
 */
@ChannelHandler.Sharable        //标示channel-handler可以被多个channel安全共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
    private Logger logger= LoggerFactory.getLogger(EchoServerHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inByteBuf=(ByteBuf) msg;
        logger.debug("channelRead()----接收到信息:{}",inByteBuf.toString(CharsetUtil.UTF_8));    //信息记录控制台
        ctx.write("收到!");   //不冲刷的写出给发送者.
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将未处理的信息冲刷到远程节点上
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);      //
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("捕获到异常",cause);
        ctx.close();//关闭当前通道
    }

}
