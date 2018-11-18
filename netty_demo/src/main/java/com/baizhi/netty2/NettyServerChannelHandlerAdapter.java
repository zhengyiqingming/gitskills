package com.baizhi.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class NettyServerChannelHandlerAdapter extends ChannelHandlerAdapter {
    /**
     * 异常事件 回调方法
     *
     * @param ctx 通道的上下文信息
     * @param cause  通道中产生的异常和错误信息
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器产生异常");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("获取到的请求数据:"+msg);
        //==============业务处理==============

        //==============处理结果==============
        //改造后的代码 异步操作 异步处理的结果对象
        ChannelFuture channelFuture = ctx.writeAndFlush(new Date());
        channelFuture.addListener(ChannelFutureListener.CLOSE);// 正常操作,操作结束后,关闭通讯管道
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE); // 失败操作,立即关闭通讯管道
        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);// 失败后触发异常

    }
}
