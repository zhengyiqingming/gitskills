package com.baizhi;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 基于netty网络应用框架的客户端模型
 */
public class NettyClientBootstrap {

    public static void main(String[] args) throws InterruptedException {
        //1. 初始化客户端的引导对象
        Bootstrap bootstrap = new Bootstrap();
        //2. 初始化IO处理的线程池
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //3. 绑定线程池
        bootstrap.group(workerGroup);
        //4. 初始化通道服务类
        bootstrap.channel(NioSocketChannel.class);
        //5. 初始化通讯管道(重点)
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {

            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new LengthFieldPrepender(2)); // 消息头的字节长度
                // 参数一：消息包的最大长度  参数二：长度域的偏移量 参数三:长度字段所占的字节⼤⼩ 参数四:长度补偿 参数五：从数据帧中跳过的字节数
                pipeline.addLast(new
                        LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                pipeline.addLast(new ObjectToByteBufEncoder());
                pipeline.addLast(new ByteBufToObjectDecoder());
                pipeline.addLast(new NettyClientChannelHandlerAdapter());
            }
        });
        //6. 连接指定服务器
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
        //7. 关闭服务
        channelFuture.channel().closeFuture().sync();
        //8. 释放资源
        workerGroup.shutdownGracefully();
    }
}
