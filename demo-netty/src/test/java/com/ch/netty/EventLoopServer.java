package com.ch.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @className: EventLoopServer
 * @Auther: ch
 * @Date: 2022/4/6 11:16
 * @Description: TODO
 */
@Slf4j
public class EventLoopServer {
    public static void main(String[] args) {
        // 创建一个独立的 EventLoopGroup
        DefaultEventLoopGroup group = new DefaultEventLoopGroup();

        // 服务器启动类
        new ServerBootstrap()
                // 添加EventLoop组
                .group(new NioEventLoopGroup(),new NioEventLoopGroup())
                // 设置服务器的channel实现
                .channel(NioServerSocketChannel.class)
                // 添加子处理程序 ,在客户端成功connect后才执行
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        // 添加handler
                        channel.pipeline().addLast(new StringDecoder());
                        channel.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg)  {
                                try {
                                    log.info("{}",msg);
                                    // 让消息传递给下一个handler
                                    ctx.fireChannelRead(msg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        channel.pipeline().addLast(group,"handler2",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("{}",msg);
                            }
                        });
                    }
                }).bind(8888);
    }
}
