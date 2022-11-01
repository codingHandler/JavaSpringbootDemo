package com.ch.Codec.自定义编解码器;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @className: ServerChannelInitializer
 * @Auther: ch
 * @Date: 2022/4/19 12:41
 * @Description: 服务端Channel指定逻辑处理Handler
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
/*        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("decoder", new StringDecoder());*/
        pipeline.addLast(new MyMessageToByteDecoder());
        //自定义Handler来处理消息
        pipeline.addLast(new ServerBusinessHandler());
    }


}
