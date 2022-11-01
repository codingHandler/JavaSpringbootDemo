package com.ch.nettyHttpTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author: ch
 * @date: 2022/8/25 16:22
 * @description: 自定义服务端处理器
 */
public class MyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("msg 类型: " + msg.getClass());
        System.out.println("客户端地址: " + ctx.channel().remoteAddress());
        //获取到
        HttpRequest httpRequest = (HttpRequest) msg;
        //获取uri, 过滤指定的资源
        URI uri = new URI(httpRequest.uri());
        if ("/favicon.ico".equals(uri.getPath())) {
            System.out.println("请求了 favicon.ico, 不做响应");
            return;
        }
        // 回复信息给浏览器[http协议]
        ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);
        // 构造一个http的响应,既httpResponse
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=utf-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        // 将构建好的response返回
        ctx.writeAndFlush(response);
    }
}
