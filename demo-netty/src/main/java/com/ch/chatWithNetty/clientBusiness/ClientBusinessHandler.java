package com.ch.chatWithNetty.clientBusiness;

import com.ch.chatWithNetty.serverBusiness.ServerOneToOneBusinessHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: ch
 * @date: 2022/8/26 15:50
 * @description: TODO
 */
public class ClientBusinessHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        ctx.pipeline().addLast(new StringDecoder());
        ctx.pipeline().addLast(new StringEncoder());
        ctx.pipeline().addLast(new ServerOneToOneBusinessHandler());
    }
}
