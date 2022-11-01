package com.ch.Codec.自定义编解码器;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: ch
 * @date: 2022/8/10 16:23
 * @description: 服务端业务逻辑
 */
public class ServerBusinessHandler extends SimpleChannelInboundHandler<Long> {
    private Integer count = 0;

/*    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String str = msg.toString();
        System.out.println("这里: " + str);
       *//* ctx.channel().eventLoop().submit(() -> {
            System.out.println("异步执行任务");
        });*//*

    }*/

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {

        System.out.println("服务器的ip=" + ctx.channel().remoteAddress());
        System.out.println("收到服务器消息=" + msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("建立连接了");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接断开");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常了");
        super.exceptionCaught(ctx, cause);
        ctx.channel().close();
    }
}
