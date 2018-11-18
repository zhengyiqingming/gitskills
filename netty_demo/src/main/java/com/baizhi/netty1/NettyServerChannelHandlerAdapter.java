package com.baizhi.netty1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

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
        System.out.println("通道中产生异常");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 字节缓冲区 等价于NIO ByteBuffer
        ByteBuf byteBuf= (ByteBuf) msg;
        System.out.println("获取到的请求数据:"+byteBuf.toString(CharsetUtil.UTF_8));
        //==============业务处理==============



        //==============处理结果==============
        byteBuf.clear();
        byteBuf.writeBytes("土豆土豆,我是地瓜".getBytes());
        ctx.writeAndFlush(byteBuf);
    }
}
