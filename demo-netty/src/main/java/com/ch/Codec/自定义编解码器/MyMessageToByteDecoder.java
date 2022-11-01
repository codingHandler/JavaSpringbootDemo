package com.ch.Codec.自定义编解码器;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;


import java.util.List;

/**
 * @author: ch
 * @date: 2022/8/28 13:21
 * @description: 自定义解码器
 */
public class MyMessageToByteDecoder extends ByteToMessageCodec<Long> {

    /**
     * 编码器：
     **/
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

    }

    /**
     * decode 会根据接收的数据，被调用多次, 直到确定没有新的元素被添加到list
     * , 或者是ByteBuf 没有更多的可读字节为止
     * 如果list out 不为空，就会将list的内容传递给下一个 channelinboundhandler处理,
     * 该处理器的方法也会被调用多次
     *
     * @param channelHandlerContext 上下文对象
     * @param byteBuf               入站的 ByteBuf
     * @param list                  List 集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyLongToByteDecoder 自定义[解码器]被调用了<---");
        //因为 long 8个字节, 需要判断有8个字节，才能读取一个long
        if(byteBuf.readableBytes() >= 8) {
            list.add(byteBuf.readLong());
        }
    }
}
