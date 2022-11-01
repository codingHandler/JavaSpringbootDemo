package com.ch.Codec.自定义编解码器;



import com.ch.chatWithNetty.clientBusiness.ClientBusinessHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: ch
 * @date: 2022/8/10 15:08
 * @description: 客户端Channel指定逻辑处理Handler
 */
public class ClientChannelInitializer extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
/*        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());*/
        pipeline.addLast(new MyMessageToByteEncoder());
        //自定义Handler来处理消息
        pipeline.addLast(new ClientBusinessHandler());
    }

}
