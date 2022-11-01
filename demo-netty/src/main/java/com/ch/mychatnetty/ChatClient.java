package com.ch.mychatnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;

/**
 * @className: ChatClient
 * @Auther: ch
 * @Date: 2022/4/20 20:14
 * @Description: 个人聊天服务器客户端
 */
public class ChatClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            // 1, 创建客户端对象
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChatClientChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();

            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        if (future.isSuccess()) {
                            System.out.println("客户端启动中...");
                        }
                        if (future.isDone()) {
                            System.out.println("客户端启动成功...OK！");
                        }
                    }
                }
            });
            // 获取通道
            Channel channel = channelFuture.channel();
            // 控制台输入
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
            channel.closeFuture().sync();
            scanner.close();
        } finally {
            group.shutdownGracefully();
        }
    }
}
