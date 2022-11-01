package com.ch.mychatnetty;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: ChatServerHandler
 * @Auther: ch
 * @Date: 2022/4/20 19:45
 * @Description: TODO
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * netty用来管理所有channel的类,一定要是静态的,因为这个类是全局共享的,如果不设置静态的,每个Handler都有自己独有的ServerHandler
     */
    private static ChannelGroup allChannels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        allChannels.forEach(channel -> {
            if (channel != ctx.channel()) {
                String topTip = simpleDateFormat.format(new Date()) + "\n";
                channel.writeAndFlush(topTip + "[" + channel.remoteAddress() + "] 说:" + msg);
            } else {
                String topTip = simpleDateFormat.format(new Date()) + "\n";
                channel.writeAndFlush(topTip + "[自己] 说:" + msg);
            }
        });
    }


    /**
     * 建立连接以后第一个调用的方法
     **/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String addr = channel.remoteAddress().toString();
        /**
         * 这里 ChannelGroup 底层封装会遍历给所有的channel发送消息
         */
        allChannels.writeAndFlush(simpleDateFormat.format(new Date()) + "\n 【用户】 " + addr + " 加入聊天室 ");
        allChannels.add(channel);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String addr = ctx.channel().remoteAddress().toString();
        System.out.println(simpleDateFormat.format(new Date()) + " \n【用户】 " + addr + " 上线 ");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("刷新了");
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String addr = ctx.channel().remoteAddress().toString();
        System.out.println(simpleDateFormat.format(new Date()) + " \n【用户】 " + addr + " 下线 ");
        // 从Channels中移除
        // allChannels.remove(ctx.channel());
        allChannels.writeAndFlush(ctx.channel().remoteAddress() + "下线了...");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("连接发生异常！" + channel.remoteAddress().toString());
        ctx.close();
    }

    /**
     * 断开连接会触发该消息
     * 同时当前channel 也会自动从ChannelGroup中被移除
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String addr = channel.remoteAddress().toString();
        /**
         * 这里 ChannelGroup 底层封装会遍历给所有的channel发送消息
         */
        allChannels.writeAndFlush(simpleDateFormat.format(new Date()) + "\n 【用户】 " + addr + " 离开了 ");
        //打印 ChannelGroup中的人数
        System.out.println("当前在线人数是:" + allChannels.size());
    }
}
