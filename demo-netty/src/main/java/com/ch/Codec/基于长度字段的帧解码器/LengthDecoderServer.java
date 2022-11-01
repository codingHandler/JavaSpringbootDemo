package com.ch.Codec.基于长度字段的帧解码器;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ch
 * @date: 2022/8/31 10:31
 * @description: 基于长度字段的帧解码器
 *
 */
@Slf4j
public class LengthDecoderServer {
    public static void main(String[] args) {
        new LengthDecoderServer().start();
    }

    private String host = "127.0.0.1";

    private int port = 9090;

    public LengthDecoderServer() {
    }

    public LengthDecoderServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup workers = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, workers);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    // 最大长度，长度偏移，长度占用字节，长度调整，剥离字节数
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 1, 0, 1));
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("服务器启动成功");
                } else {
                    System.out.println("服务器启动失败"+future.cause());
                }
            });
            //监听channel关闭
            channelFuture.channel().closeFuture().sync();
            channelFuture.addListener(future -> {
                if (future.isCancelled()) {
                    System.out.println("服务器正在关闭..");
                }
                if (future.isCancellable()) {
                    System.out.println("服务器已经关闭..OK");
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
