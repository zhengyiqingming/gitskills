package com.baiozhi.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 真正意义上的NIO的网络编程(服务器模型) 单线程
 *
 *    网络 + 线程 + IO
 *
 * netty简化Java NIO的网络编程
 *
 */
public class NIOServer02 {
    public static void main(String[] args) throws IOException {
        //1. 初始化服务器的引导对象(通道)
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);

        //2. 获取选择器
        Selector selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);// 服务器被动接受客户端的请求

        //3.事件列表处理
        System.out.println("服务器已启动!在9999端口等待客户端的连接......");
        while (true){
            //事件列表中SelectionKey对象的数量
            int select=selector.select();
            if(select!=0){
                // 就绪等待IO处理的通道集
                Set<SelectionKey>  selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //===============IO处理开始============
                    if(selectionKey.isAcceptable()){// 接受请求事件 事件已发生
                        System.out.println("接收请求事件发生");
                        // 创建连接对象
                        ServerSocketChannel ssc= (ServerSocketChannel) selectionKey.channel();
                        SocketChannel sc = ssc.accept();//始终是同一个sc
                        sc.configureBlocking(false);
                        sc.register(selector,SelectionKey.OP_READ);
                    }else if(selectionKey.isReadable()){
                        System.out.println("读事件发生");
                        SocketChannel sc= (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer=ByteBuffer.allocate(1024);
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        while(true){
                            int read= sc.read(buffer);
                            if(read==-1)break;
                            buffer.flip();
                            baos.write(buffer.array(),0,read);
                            buffer.clear();
                        }
                        System.out.println("获取到的请求数据:"+new String(baos.toByteArray()));
                        sc.shutdownInput();
                        sc.register(selector,SelectionKey.OP_WRITE);
                    }else if(selectionKey.isWritable()){// 写事件  响应数据
                        System.out.println("写事件发生");
                        SocketChannel sc= (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer=ByteBuffer.allocate(1024);
                        buffer.put("土豆土豆,我是番茄".getBytes());
                        buffer.flip();
                        sc.write(buffer);
                        sc.shutdownOutput();
                        //释放资源
                        sc.close();
                    }
                    //===============IO处理结束============
                    // 从事件列表中移除IO处理完成的通道
                    iterator.remove();//移除selectionKey
                }
            }
        }
    }
}
