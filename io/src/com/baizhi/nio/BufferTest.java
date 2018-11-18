package com.baizhi.nio;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        //1.初始化缓冲区
        // position = 0  | limit = capacity=1024
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position()+"|"+byteBuffer.limit()+"|"+byteBuffer.capacity());

        //2. 存放2个字节的数据
        byteBuffer.put("ab".getBytes());
        // position = 2  | limit = capacity=1024
        System.out.println(byteBuffer.position()+"|"+byteBuffer.limit()+"|"+byteBuffer.capacity());

        //3. 获取2个字节的数据
        //注意:读写状态的切换
        byteBuffer.flip();
        // position = 0  | limit = 2  capacity=1024
        System.out.println(byteBuffer.position() + " | " + byteBuffer.limit() +" | "+byteBuffer.capacity());
        System.out.println(byteBuffer.get());// 默认值 0
        System.out.println(byteBuffer.get()); // 默认值 0

        System.out.println(byteBuffer.position() + " | " + byteBuffer.limit() +" | "+byteBuffer.capacity());
        byteBuffer.clear();
        System.out.println(byteBuffer.get()); // 默认值 0
        // position = 1  | limit =capacity=1024
        System.out.println(byteBuffer.position() + " | " + byteBuffer.limit() +" | "+byteBuffer.capacity());

    }
}
