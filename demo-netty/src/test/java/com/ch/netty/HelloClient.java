package com.ch.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @className: HelloClient
 * @Auther: ch
 * @Date: 2022/4/5 14:38
 * @Description: TODO
 */
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        // 1,启动类
        new Bootstrap()
                // 2,添加EventLoop
                .group(new NioEventLoopGroup())
                // 3,设置客户端选择的channel实现
                .channel(NioSocketChannel.class)
                // 4,添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override // 在连接建立后调用
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 5,连接到服务器
                .connect(new InetSocketAddress("localhost",8888))
                .sync()
                .channel()
                .writeAndFlush("hello word");
    }
}
