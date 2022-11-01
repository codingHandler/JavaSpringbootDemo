package com.ch.Codec.行解码器;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author: ch
 * @date: 2022/8/31 10:31
 * @description: 行解码器服务端
 * 行解码器的是通过分隔符对数据进行拆分来解决粘包半包问题的
 */
@Slf4j
public class LineDecoderServer {
    public static void main(String[] args) {
        new LineDecoderServer().start();
    }

    private String host = "127.0.0.1";

    private int port = 8080;

    public LineDecoderServer() {
    }

    public LineDecoderServer(String host, int port) {
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
                    // 通过行解码器对粘包数据进行拆分，以 \n 为分隔符
                    // 将分隔符放入ByteBuf中
                    //ByteBuf bufSet = ch.alloc().buffer().writeBytes("\\n".getBytes(StandardCharsets.UTF_8));
                    // 需要指定最大长度和分隔符
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(64));
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
