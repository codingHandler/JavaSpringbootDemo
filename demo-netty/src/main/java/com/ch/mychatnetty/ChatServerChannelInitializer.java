package com.ch.mychatnetty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @className: ChatServerHandler
 * @Auther: ch
 * @Date: 2022/4/20 19:41
 * @Description:
 */
public class ChatServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        // 获取管道ChannelPipeline对象
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("myHandler",new ChatServerHandler());
    }
}
