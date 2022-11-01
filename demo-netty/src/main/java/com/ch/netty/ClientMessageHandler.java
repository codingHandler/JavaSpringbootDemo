package com.ch.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @className: ClientMessageHandler
 * @Auther: ch
 * @Date: 2022/4/19 13:18
 * @Description: TODO
 */
public class ClientMessageHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 处理收到的消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg)
            throws Exception {
        System.out.println(msg);

    }
    /**
     * 连接异常后触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();

    }

}
