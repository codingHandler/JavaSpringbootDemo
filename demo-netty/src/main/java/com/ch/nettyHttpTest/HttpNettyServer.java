package com.ch.nettyHttpTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: ch
 * @date: 2022/8/25 16:01
 * @description: http服务端
 */
public class HttpNettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup workers = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 指定线程模型
            serverBootstrap.group(boss, workers);
            // 指定IO模型
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 指定通道需要的处理器
            serverBootstrap.childHandler(new HttpInitializerHandler());
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            // 添加端口绑定监听
            channelFuture.addListener((future) -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        System.out.println("服务器启动成功.");
                    } else {
                        System.out.println("服务器启动失败!");
                        future.cause().getCause().printStackTrace();
                    }
                }
            });
            // 添加关闭通道监听
            ChannelFuture closeFuture = channelFuture.channel().closeFuture().sync();
            closeFuture.addListener((future) -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        System.out.println("服务器关闭成功.");
                    } else {
                        System.out.println("服务器关闭失败!");
                        future.cause().getCause().printStackTrace();
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            workers.shutdownGracefully();
        }
    }
}
