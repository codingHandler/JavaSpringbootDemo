package com.ch.chatWithNetty;

import com.ch.chatWithNetty.clientBusiness.ClientBusinessHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: ch
 * @date: 2022/8/26 15:48
 * @description: TODO
 */
public class ChatClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        ch.pipeline().addLast(new ObjectEncoder());
        ch.pipeline().addLast(new ClientBusinessHandler());
    }
}
