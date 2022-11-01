package com.ch.heartBeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author: ch
 * @date: 2022/8/30 12:08
 * @description: TODO
 */
public class HeartBeatMessageInitializeHandler extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
        //加入一个对空闲检测进一步处理的handler(自定义)
        pipeline.addLast(new ServerHeartBeatHandler());

    }
}
