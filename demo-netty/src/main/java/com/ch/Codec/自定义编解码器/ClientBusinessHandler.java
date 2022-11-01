package com.ch.Codec.自定义编解码器;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author: ch
 * @date: 2022/8/10 16:26
 * @description: TODO
 */
public class ClientBusinessHandler extends ChannelInboundHandlerAdapter {
    private Integer count = 0;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送10条数据 hello,server 编号
        for (int i = 0; i < 10; ++i) {
            System.out.println("MyClientHandler 发送数据");
            //发送的是一个long
            ctx.writeAndFlush(123456L);
        }
    }
}
