package com.ch.ChannelHandler生命周期;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
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
public class LifeCycleClient {
    public static void main(String[] args) throws InterruptedException {
        // 客户端一个事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 1,创建客户端启动对象
        Bootstrap bootstrap = new Bootstrap();
        // 2,添加EventLoop
        bootstrap.group(group);
        // 3,设置客户端通道的的channel实现
        bootstrap.channel(NioSocketChannel.class);
        // 4,添加处理器
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override // 在连接建立后调用
            protected void initChannel(NioSocketChannel channel) throws Exception {
                channel.pipeline().addLast(new StringEncoder());
            }
        });
        InetSocketAddress address = new InetSocketAddress("localhost", 8666);
        // 5,客户端连接到服务器,同步生成一个ChannelFuture对象
        ChannelFuture channelFuture = bootstrap.connect(address).sync();
        // 6,获取通道
        Channel channel = channelFuture.channel();
        // 7,通过通道给服务端发送数据
        channel.writeAndFlush("hello word");
        // 8,给关闭通道进行监听
        channel.closeFuture().sync();
    }
}
