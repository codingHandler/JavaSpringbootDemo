package com.ch.chatWithNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author: ch
 * @date: 2022/8/26 14:08
 * @description: 聊天客户端实现
 */
@Slf4j
public class ChatClient {
    private String host = "127.0.0.1";
    private int port = 8888;

    public ChatClient() {

    }

    public ChatClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void startUp() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            ChannelFuture channelFuture = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientChannelInitializer())
                    .connect(new InetSocketAddress(host, port)).sync();
            // 监听
            channelFuture.addListener(future -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        log.info("客户端启动成功.");
                    } else {
                        log.error("客户端启动失败!", future.cause().getCause());
                    }
                }
            });
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.next();
                Channel channel = channelFuture.channel();
                channel.writeAndFlush(msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatClient().startUp();
    }
}
