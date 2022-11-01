package com.ch.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @className: TestPipelineServer
 * @Auther: ch
 * @Date: 2022/4/7 09:59
 * @Description: ChannelHandler 用来处理 Channel 上的各种事件的执行流程
 */
@Slf4j
public class TestPipelineServer {
    public static void main(String[] args) {
        // 创建启动器
        new ServerBootstrap()
                // 设置EventLoop组
                .group(new NioEventLoopGroup())
                // 设置通道实现
                .channel(NioServerSocketChannel.class)
                // 添加子处理程序
                .childHandler(new ChannelInitializer<NioSocketChannel>() {   // 通道初始化

                    @Override
                    protected void initChannel(NioSocketChannel channel) {
                        // 1,通过channel获取pipeline对象
                        ChannelPipeline pipeline = channel.pipeline();
                        try {
                            // 2,添加handler处理器 : head->...->tail
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
                            pipeline.addLast("In-BoundHandler1", new ChannelInboundHandlerAdapter() {
                                @Override // 与客户端建立连接成功时调用
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    log.info("连接成功");
                                    super.channelActive(ctx);
                                }

                                @Override // 触发read事件时调用
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.info("In-BoundHandler1====> {}", msg);
                                    super.channelRead(ctx, msg);
                                }
                            });
                            // 入站处理器通常是 ChannelInboundHandlerAdapter 的子类，主要用来读取客户端数据，写回结果
                            pipeline.addLast("In-BoundHandler2", new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    log.info("In-BoundHandler2====> {}", msg.toString());
                                    super.channelRead(ctx, msg);
                                }
                            });

                            pipeline.addLast("In-BoundHandler3", new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    // channel.writeAndFlush()和ctx.writeAndFlush()的区别,
                                    // channel.writeAndFlush会从尾部(tail)节点开始调用出站处理器
                                    // ctx.writeAndFlush() 从当前处理器节点开始调用出站处理器
                                    log.info("In-BoundHandler3====> {}", msg.toString());
                                    channel.writeAndFlush(msg);
                                }
                            });
                            // 出站处理器通常是 ChannelOutboundHandlerAdapter 的子类，主要对写回结果进行加工
                            pipeline.addLast("Out-BoundHandler1", new ChannelOutboundHandlerAdapter() {
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    log.info("Out-BoundHandler1>>>> {}", msg.toString() + "处理结果1");
                                    ctx.writeAndFlush(msg, promise);
                                    //ctx.channel().write("处理结果1", promise);
                                }
                            });
                            pipeline.addLast("Out-BoundHandler2", new ChannelOutboundHandlerAdapter() {
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    msg = msg.toString() + "处理结果2>>>";
                                    log.info("Out-BoundHandler2>>>> {}", msg.toString());
                                    ctx.writeAndFlush(msg, promise);
                                    //super.write(ctx, msg, promise);
                                    //ctx.channel().write("处理结果2", promise);
                                }
                            });
                            pipeline.addLast("Out-BoundHandler3", new ChannelOutboundHandlerAdapter() {
                                @Override
                                public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                    msg = msg.toString() + "处理结果3>>>";
                                    log.info("Out-BoundHandler3>>>> {}", msg.toString());
                                    ctx.writeAndFlush(msg, promise);
                                    //super.write(ctx, msg, promise);
                                    // ctx.channel().write("处理结果3", promise);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .bind(8888);

    }
}
