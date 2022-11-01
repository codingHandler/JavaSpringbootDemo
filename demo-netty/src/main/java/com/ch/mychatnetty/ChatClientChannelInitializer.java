package com.ch.mychatnetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @className: ChatClientChannelInitializer
 * @Auther: ch
 * @Date: 2022/4/20 20:17
 * @Description: 客户端子程序初始化处理器
 */
public class ChatClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 获取ChannelPipeline管道
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("encoder",new StringEncoder());
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("myClientHandler",new ChatClientHandler());
    }
}
