package com.ch.chatWithNetty;

import com.ch.chatWithNetty.serverBusiness.ServerGroupBusinessHandler;
import com.ch.chatWithNetty.serverBusiness.ServerOneToOneBusinessHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author: ch
 * @date: 2022/8/26 15:16
 * @description: TODO
 */
public class ChatServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        ch.pipeline().addLast(new ObjectEncoder());
        ch.pipeline().addLast(new ServerGroupBusinessHandler());
        ch.pipeline().addLast(new ServerOneToOneBusinessHandler());
    }
}
