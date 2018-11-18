package com.baiozhi.nio;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 伪NIO网络编程模型(服务器端)
 *      改变阻塞问题? 线程利用不高问题? 没解决
 *
 * 通道 + 缓冲区 + 选择器(暂不涉及)
 */
public class NIOServer01 {
    public static void main(String[] args) throws IOException {
        //1. 获取NIO服务器的引导对象 通道
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(true);// 阻塞通道 默认都是阻塞通道
        ExecutorService threadPool= Executors.newFixedThreadPool(100);
        System.out.println("服务器已启动!在8888端口等待客户端的连接.......");
        while(true){
            //2. 等待客户端的连接 建立连接对象
            final SocketChannel socketChannel=serverSocketChannel.accept();
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        //3. IO操作(请求/响应的处理)
                        ByteBuffer buffer = ByteBuffer.allocate(1024);//分配1024字节
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        while(true){
                            int read=socketChannel.read(buffer);
                            if(read==-1) break;
                            buffer.flip();
                            baos.write(buffer.array());
                            buffer.clear();
                        }
                        System.out.println("服务器接收到的请求数据:"+new String(baos.toByteArray()));
                        socketChannel.shutdownInput();

                        buffer.put("地瓜地瓜,我是土豆".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);// 将字节缓冲区中可读的数据写到通道中
                        socketChannel.shutdownOutput();

                        //4. 释放资源
                        socketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
