package com.ch.nettyWebsocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ch
 * @date: 2022/8/27 13:15
 * @description: netty实现websocket
 */
@Slf4j
public class NettyWebsocketServer {
    public static void main(String[] args) {
        new NettyWebsocketServer(7878).startup();
    }

    private int port;

    public NettyWebsocketServer() {
        this.port = 7000;
    }

    public NettyWebsocketServer(int port) {
        this.port = port;
    }

    public void startup() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture channelFuture = bootstrap.group(boss, worker)
                    // 指定IO模型
                    .channel(NioServerSocketChannel.class)
                    // 指定业务业务处理器
                    .childHandler(new NettyWebsocketChannelInitializer())
                    // 绑定端口
                    .bind(port).sync();
            // 监听
            channelFuture.addListener(future -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        System.out.println("服务器启动成功!");
                    } else {
                        System.out.println("服务器启动失败: "+ future.cause().getCause());
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
