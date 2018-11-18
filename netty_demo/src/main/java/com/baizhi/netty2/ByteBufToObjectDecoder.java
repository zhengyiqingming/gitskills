package com.baizhi.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.lang.SerializationUtils;

import java.util.List;
/**
 * 解码器
 *  byteBuf ---> Object
 *
 */
public class ByteBufToObjectDecoder extends MessageToMessageDecoder {
    /**
     * 解码方法
     * @param channelHandlerContext
     * @param o  byteBuf
     * @param list 输出列表
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        //1. 将o强转为真实类型ByteBuf
        ByteBuf byteBuf = (ByteBuf)o;
        //2. 获取ByteBuf中可读的数据 byte[]
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        //3. 将byte[]反序列化为Object对象
        Object obj = SerializationUtils.deserialize(bytes);
        //4. 将反序列化后的结果添加到输出类表中
        list.add(obj);
    }
}
