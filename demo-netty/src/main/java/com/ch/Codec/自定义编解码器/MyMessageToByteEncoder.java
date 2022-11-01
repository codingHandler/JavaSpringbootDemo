package com.ch.Codec.自定义编解码器;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: ch
 * @date: 2022/8/28 13:17
 * @description: 自定义编码器
 */
public class MyMessageToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long data, ByteBuf byteBuf) throws Exception {
        System.out.println("MyLongToByteEncoder 自定义 [编码器] 被调用了--->");
        System.out.println("data = " + data);
        byteBuf.writeLong(data);
    }
}
