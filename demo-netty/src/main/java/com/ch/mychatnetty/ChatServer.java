package com.ch.mychatnetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @className: ChatServer
 * @Auther: ch
 * @Date: 2022/4/20 19:35
 * @Description: 个人聊天服务端
 */
public class ChatServer {
    public static void main(String[] args) throws InterruptedException {
        // 创建两个EventLoop组
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        // 不给大小默认(cup核心数*2)
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            // 1,创建服务端对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker);
            // 2,设置服务器端使用的Channel类型
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 3,配置子处理程序handler,与客户端建立时调用
            serverBootstrap.childHandler(new ChatServerChannelInitializer());
            // 3,绑定
            ChannelFuture channelFuture = serverBootstrap.bind(6666).sync();
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("服务器正在启动...");
                    }
                    if (future.isDone()) {
                        System.out.println("服务器启动成功...OK");
                    }

                }
            });

            // 5,同步监听通道关闭事件
            ChannelFuture sync = channelFuture.channel().closeFuture().sync();
            sync.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future)
                        throws Exception {
                    if (future.isCancelled()) {
                        System.out.println("服务器正在关闭..");
                    }
                    if (future.isCancellable()) {
                        System.out.println("服务器已经关闭..OK");
                    }

                }
            });
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
