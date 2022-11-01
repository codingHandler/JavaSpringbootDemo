package com.ch.heartBeat;

import com.ch.nettyHttpTest.HttpClientInitHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author: ch
 * @date: 2022/8/25 16:01
 * @description: http客户端
 */
public class HeartBeatClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap clientBootstrap = new Bootstrap();
            // 指定线程模型
            clientBootstrap.group(group);
            // 通道IO模型
            clientBootstrap.channel(NioSocketChannel.class);
            // 客户端
            clientBootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                   ch.pipeline().addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                   ch.pipeline().addLast( new ClientHeartBeatHandler());
                }
            });

            ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1", 8000)).sync();
            connect.addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("连接成功>>>");
                }else {
                    System.out.println("连接失败!!!");
                }
            });

            //connect.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
