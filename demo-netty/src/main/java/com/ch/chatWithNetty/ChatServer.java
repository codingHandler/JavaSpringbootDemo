package com.ch.chatWithNetty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ch
 * @date: 2022/8/26 14:08
 * @description: 聊天服务端实现
 */
@Slf4j
public class ChatServer {
    /**
     * 服务器端口号
     */
    private Integer port = 8888;

    public ChatServer() {
    }

    public ChatServer(Integer port) {
        this.port = port;
    }

    public void startUp() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            // 指定线程模型
            ChannelFuture channelFuture = bootstrap.group(boss, worker)
                    // 指定IO模型
                    .channel(NioServerSocketChannel.class)
                    // 指定worker处理Handler处理逻辑
                    .childHandler(new ChatServerChannelInitializer())
                    // 绑定端口
                    .bind(port).sync();

            // 监听
            channelFuture.addListener(future -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        log.info("服务端启动成功.");
                    } else {
                        log.error("服务端启动失败!", future.cause().getCause());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatServer().startUp();
    }

}
