package com.baizhi;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

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
        System.out.println("客户端异常......");
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
        // 请求数据对象中封装了远程方法的调用信息
        ctx.writeAndFlush(new RequestData(HelloService.class,"sayHello",new Object[]{"zs"},new Class[]{String.class}));
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
        ResponseData responseData= (ResponseData) msg;
        System.out.println("远程方法调用正常结果:"+responseData.getResult());
    }
}
