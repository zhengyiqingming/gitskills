package com.baizhi;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang.SerializationUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 编码器: Object ---> ByteBuf
 */
public class ObjectToByteBufEncoder extends MessageToMessageEncoder {

    /**
     * 编码方法
     *
     * @param channelHandlerContext 通道处理器的上下文独享
     * @param o   Object 编码对象
     * @param list  输出列表
     * @throws Exception
     */
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        // 1. 创建字节缓冲区
        ByteBuf byteBuf = Unpooled.buffer();

        // 2. Object ---> byte[]
        byte[] bytes = SerializationUtils.serialize((Serializable) o);

        // 3．包装
        byteBuf.writeBytes(bytes);

        // 4. 将字节缓冲区添加到输出列表中进行数据的传输
        list.add(byteBuf);
    }
}
