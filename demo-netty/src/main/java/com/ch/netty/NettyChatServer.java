package com.ch.netty;

import com.sun.org.apache.xml.internal.security.Init;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @className: NettyChatServer
 * @Auther: ch
 * @Date: 2022/4/19 12:36
 * @Description: netty群聊 服务端
 */
public class NettyChatServer {


    public static void main(String[] args) throws Exception {
        new NettyChatServer().init(8888);
    }

    private void init(int port) throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(16);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ServerChannelInitializer());
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("服务器正在启动...");
                    }
                    if (future.isDone()) {
                        System.out.println("服务器启动成功...OK");
                    }

                }
            });
            //监听channel关闭
            channelFuture.channel().closeFuture().sync();
            channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {

                @Override
                public void operationComplete(Future<? super Void> future)
                        throws Exception {
                    if (future.isCancelled()) {
                        System.out.println("服务器正在关闭..");
                    }
                    if (future.isCancellable()) {
                        System.out.println("服务器已经关闭..OK");
                    }

                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
