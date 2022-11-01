package com.ch.Codec.自定义编解码器;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author: ch
 * @date: 2022/8/10 14:16
 * @description: 客户端
 */
public class NettyClient {
    static final String host = "127.0.0.1";
    static final int port = 8111;

    public static void main(String[] args) {
        try {
            NioEventLoopGroup worker = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            // 指定线程模型
            bootstrap.group(worker);
            // 指定IO模型
            bootstrap.channel(NioSocketChannel.class);
            // 给客户端Channel指定处理逻辑Handler
            bootstrap.handler(new ClientChannelInitializer());
            // 连接服务端
            ChannelFuture channelFuture =
                    bootstrap.connect(host, port);
            // 监听连接结果
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接成功!");
                } else {
                    System.out.println("连接失败,开始重连");
                    // 失败重连方法
                    connect(bootstrap, host, port);
                }
            });
        } finally {

        }
    }

    /***
     * 失败重连
     * @param bootstrap
     * @param host
     * @param port
     */
    private static void connect(Bootstrap bootstrap, String host, int port) {
        try {
            bootstrap.connect(host, port).addListener(future -> {
                if (future.isDone()){
                    if (future.cause()!=null){
                        System.out.println(future.cause().getMessage());
                    }
                    if (future.isSuccess()) {
                        System.out.println("连接成功!");
                    } else {
                        System.out.println("重连失败,重复尝试中..");
                        // 获取EventLoopGroup线程组
                        EventLoopGroup thread = bootstrap.config().group();
                        // 定时任务,每隔5秒重连一次
                        thread.schedule(() -> {
                            connect(bootstrap, host, port);
                        }, 5, TimeUnit.SECONDS);

                    }
                }

            });
        }finally {

        }
    }
}
