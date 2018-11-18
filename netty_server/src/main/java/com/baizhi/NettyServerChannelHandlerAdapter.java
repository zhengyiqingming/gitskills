package com.baizhi;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * netty服务器的通道处理器类
 */
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
        System.out.println("服务器产生异常......");
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 通道发生读事件 回调方法(请求数据到达) 并且响应数据
     *
     * @param ctx
     * @param msg  通道中传输的数据 ByteBuf
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 请求数据对象 封装了远程方法的调用信息
            RequestData requestData= (RequestData) msg;

            Class<?> targetInterface=requestData.getTargetInterface();

            Method method=targetInterface.getMethod(requestData.getTargetMethodName(),requestData.getParameterTypes());

            Object result = method.invoke(new HelloServiceImpl(), requestData.getArgs());

            ChannelFuture channelFuture = ctx.writeAndFlush(new ResponseData(result));

            channelFuture.addListener(ChannelFutureListener.CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.writeAndFlush(new ResponseData(e));
        }
    }
}
