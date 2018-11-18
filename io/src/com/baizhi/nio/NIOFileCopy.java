package com.baizhi.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.nio.ByteBuffer.allocate;

public class NIOFileCopy {
    public static void main(String[] args) throws IOException {
        //1.获取通道
        FileChannel inputChannel=new FileInputStream("F:\\1.jpg").getChannel();
        FileChannel outputChannel=new FileOutputStream("E:\\3.jpg").getChannel();

        //准备缓冲区
        ByteBuffer buffer= allocate(1024);

        //3.数据处理
        while(true){
            // 读取的字节数量 read == -1 数据读完
            int read=inputChannel.read(buffer);
            if(read==-1)break;
            buffer.flip();// 读写状态切换 将写状态切换为读状态
            outputChannel.write(buffer);// position-limit指针间
            buffer.clear();
        }
        //释放资源
        outputChannel.close();
        inputChannel.close();
     }
}
