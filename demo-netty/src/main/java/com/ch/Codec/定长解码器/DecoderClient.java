package com.ch.Codec.定长解码器;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ch
 * @date: 2022/8/30 21:10
 * @description: 定长解码器客户端
 * 客户端和服务器约定一个最大长度，保证客户端每次发送的数据长度都不会大于该长度。若发送数据长度不足则需要补齐至该长度
 */
@Slf4j
public class DecoderClient {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    log.debug("connected...");

                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            log.debug("sending...");
                            // 约定最大长度为16
                            final int maxLength = 16;
                            // 被发送的数据
                            char c = 'a';
                            // 向服务器发送10个报文
                            for (int i = 0; i < 10; i++) {
                                ByteBuf buffer = ctx.alloc().buffer(maxLength);
                                // 定长byte数组，未使用部分会以0进行填充
                                byte[] bytes = new byte[maxLength];
                                // 生成长度为0~15的数据
                                for (int j = 0; j < (int) (Math.random() * (maxLength - 1)); j++) {
                                    bytes[j] = (byte) c;
                                }
                                buffer.writeBytes(bytes);
                                c++;
                                // 将数据发送给服务器
                                ctx.writeAndFlush(buffer);
                            }
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error("client error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
