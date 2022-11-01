package com.ch.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;

/**
 * @author: ch
 * @date: 2022/8/31 16:43
 * @description: // 该指令一共有3部分，每条指令之后都要添加回车与换行符
 * *3\r\n
 * // 第一个指令的长度是3
 * $3\r\n
 * // 第一个指令是set指令
 * set\r\n
 * // 下面的指令以此类推
 * $4\r\n
 * name\r\n
 * $5\r\n
 * Nyima\r\n
 */
public class RedisProtocolClient {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler());
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // 回车与换行符
                            final byte[] LINE = {'\r', '\n'};
                            ByteBuf buffer = ctx.alloc().buffer();
                            // 多少个指令,set name zch
                            buffer.writeBytes("*3".getBytes());
                            buffer.writeBytes(LINE);
                            // set\r\n
                            buffer.writeBytes("$3".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("set".getBytes());
                            buffer.writeBytes(LINE);
                            // name
                            buffer.writeBytes("$4".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("name".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("$3".getBytes());
                            buffer.writeBytes(LINE);
                            buffer.writeBytes("zch".getBytes());
                            buffer.writeBytes(LINE);
                            ctx.writeAndFlush(buffer);
                        }
                    });
                }

                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    ByteBuf byteBuf = (ByteBuf) msg;
                    System.out.println(byteBuf.toString(Charset.defaultCharset()));
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("192.168.2.103", 6379).sync();
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else {
                    System.out.println("连接失败");
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
