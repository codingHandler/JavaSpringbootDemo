package com.ch.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;


/**
 * @className: EventLoopClient
 * @Auther: ch
 * @Date: 2022/4/6 11:23
 * @Description: TODO
 */
@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        // 带有 Future，Promise 的类型都是和异步方法配套使用，用来处理结果
        ChannelFuture channelFuture = new Bootstrap() // 启动类
                // 添加EventLoop组
                .group(new NioEventLoopGroup())
                // 设置客户端选择的channel实现
                .channel(NioSocketChannel.class)
                // 添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override // 在与服务器端建立连接后被调用
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        // 添加编码handler处理器
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 连接到服务器
                // 异步非阻塞,main发起了调用,真正执行connect的是nio线程
                .connect(new InetSocketAddress("localhost", 8888));

        // 使用sync()方法同步处理结果
       /* channelFuture.sync(); // 阻塞当前线程,直到nio线程连接建立完毕
        Channel channel = channelFuture.channel();
        log.info("{}", channel);
        channel.writeAndFlush("hello");*/


        // 使用addListener(回调对象)方法异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            @Override // 在nio线程连接建立好之后,会调用operationComplete
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel channel = channelFuture.channel();
                channel.writeAndFlush("通过addListener回调方式异步处理结果");
            }
        });


    }
}
