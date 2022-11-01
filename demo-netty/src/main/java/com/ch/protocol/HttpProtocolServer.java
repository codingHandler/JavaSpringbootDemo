package com.ch.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LoggingHandler;

import static org.springframework.http.HttpHeaders.CONTENT_LENGTH;

/**
 * @author: ch
 * @date: 2022/8/31 17:14
 * @description: TODO
 */
public class HttpProtocolServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    // 日志
                    ch.pipeline().addLast(new LoggingHandler());
                    // http编解码器
                    ch.pipeline().addLast(new HttpServerCodec());
                    // 自定义处理器Handler
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            if (msg instanceof HttpRequest){ // 请求行,请求头
                                DefaultHttpRequest request =  (DefaultHttpRequest)msg;
                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(),HttpResponseStatus.OK);
                                byte[] bytes = "<h1>Hello World</h1>".getBytes();
                                response.headers().setInt(CONTENT_LENGTH,bytes.length);
                                response.content().writeBytes(bytes);
                                ctx.writeAndFlush(response);
                            }
                            if (msg instanceof HttpContent){ // 请求体
                                LastHttpContent body =  (LastHttpContent)msg;
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(7979).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("启动成功!");
                } else {
                    System.out.println("启动失败!");
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
