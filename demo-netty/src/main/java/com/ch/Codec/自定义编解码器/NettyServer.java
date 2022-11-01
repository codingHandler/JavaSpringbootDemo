package com.ch.Codec.自定义编解码器;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author: ch
 * @date: 2022/8/10 14:17
 * @description: TODO
 */
public class NettyServer {
    static final String host = "127.0.0.1";
    static final int port = 8111;
    public static void main(String[] args) {
        try {
            NioEventLoopGroup boss = new NioEventLoopGroup();
            NioEventLoopGroup worker = new NioEventLoopGroup();

            ServerBootstrap bootstrap = new ServerBootstrap();
            //指定线程模型
            bootstrap.group(boss, worker);
            // 指定IO模型
            bootstrap.channel(NioServerSocketChannel.class);
            // 指定客户端通道需要处理的业务逻辑
            bootstrap.childHandler(new ServerChannelInitializer());
            // 绑定端口
            ChannelFuture channel = bootstrap.bind(port);
            ChannelFuture syncChannelFuture = channel.sync();
            // 注册监听器
            syncChannelFuture.addListener(future -> {
                if (future.isDone()) {
                    if (future.isSuccess()) {
                        System.out.println("服务器启动成功...");
                    } else if (future.isCancelled()) {
                        System.out.println("任务被取消...");
                    } else if (future.cause() != null) {
                        System.out.println("执行出错：" + future.cause().getMessage());
                    }
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
