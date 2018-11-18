package com.baizhi;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * 基于netty网络应用框架的服务器引导类
 *
 */
public class NettyServerBootstrap {
    public static void main(String[] args) throws InterruptedException {
        //1. 初始化服务器的引导对象
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2. 初始化请求转发和IO处理的线程池
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //3. 绑定线程池
        serverBootstrap.group(bossGroup,workerGroup);
        //4. 初始化通道服务类
        serverBootstrap.channel(NioServerSocketChannel.class);
        //5. 初始化通讯管道(重点)
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            /**
             * 组装通道管道和通道处理器
             * @param socketChannel
             * @throws Exception
             */
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast(new LengthFieldPrepender(2)); // 消息头的字节长度
                // 参数一：消息包的最大长度  参数二：长度域的偏移量 参数三:长度字段所占的字节⼤⼩ 参数四:长度补偿 参数五：从数据帧中跳过的字节数
                pipeline.addLast(new
                        LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                pipeline.addLast(new ObjectToByteBufEncoder());
                pipeline.addLast(new ByteBufToObjectDecoder());
                pipeline.addLast(new NettyServerChannelHandlerAdapter());
            }
        });
        System.out.println("服务器已启动,在8888端口等待客户端连接......");
        //6. 绑定端口 并开启服务
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
        //7. 关闭服务
        channelFuture.channel().closeFuture().sync();
        //8. 释放资源
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
