package com.baizhi.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Date;

/**
 * netty的client的通道处理器类
 */
public class NettyClientChannelHandlerAdapter extends ChannelHandlerAdapter {
    /**
     * 异常事件的回调方法
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端异常...");
        cause.printStackTrace();
        ctx.close();
    }
    /**
     * 客户端和服务器建立连接(连接就绪) 发送请求数据
     *
     * Object ---> ByteBuf
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("熊大,光头强又来了,可咋办啊!!!!");
    }
    /**
     * 可读事件 回调方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Date date= (Date) msg;
        System.out.println("接收的日期结果:"+date);
        ctx.close();
    }
}
