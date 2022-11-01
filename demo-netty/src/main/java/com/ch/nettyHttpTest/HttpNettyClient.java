package com.ch.nettyHttpTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: ch
 * @date: 2022/8/25 16:01
 * @description: http客户端
 */
public class HttpNettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();

            // 指定线程模型
            clientBootstrap.group(group);
            // 通道IO模型
            clientBootstrap.channel(NioSocketChannel.class);
            // 客户端
            clientBootstrap.handler(new HttpClientInitHandler());

            ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 6667)).sync();
            connect.addListener(future -> {

            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }


    }
}
