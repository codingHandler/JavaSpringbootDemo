package com.ch.nettyHttpTest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: ch
 * @date: 2022/8/25 16:03
 * @description: TODO
 */
public class HttpInitializerHandler extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        // 加入一个netty提供的HttpServerCodec 编解码器
        ch.pipeline().addLast("httpCodec",new HttpServerCodec());
        // 加入一个自己的handler
        ch.pipeline().addLast("myServerHandler",new MyServerHandler());
    }
}
