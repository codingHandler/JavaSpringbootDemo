package com.ch.ChannelHandler生命周期;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @className: TestChannelHandlerLifeCycleServer
 * @Auther: ch
 * @Date: 2022/4/20 14:23
 * @Description: ChannelHandler生命周期详解服务器
 */
public class TestChannelHandlerLifeCycleServer {
    public static void main(String[] args) throws InterruptedException {
        // bossGroups 只处理连接请求,真正和客户端处理的业务会交给workerGroups完成
        // bossGroups和workerGroups含有的子线程NioEventLoop的个数(默认是实际cpu核心数*2)
        NioEventLoopGroup bossGroups = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroups = new NioEventLoopGroup();
        try {
            // 创建服务端启动对象,配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置两个线程组,bossGroups和workerGroups
            bootstrap.group(bossGroups, workerGroups);
            // 设置服务器端通道的实现
            bootstrap.channel(NioServerSocketChannel.class);
            // 给workerGroups添加子处理器,在客户端成功connect后才会执行
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override //初始化通道
                protected void initChannel(NioSocketChannel ch) throws Exception {

                    ch.pipeline().addLast("handler1",new LifeCycleTestHandler());
                }
            });
            // 绑定端口并且同步,生成一个ChannelFuture对象
            ChannelFuture sync = bootstrap.bind(8666).sync();
            // 对通道关闭事件进行监听
            sync.channel().closeFuture().sync();
        } finally {
            bossGroups.shutdownGracefully();
            workerGroups.shutdownGracefully();
        }
    }
}
