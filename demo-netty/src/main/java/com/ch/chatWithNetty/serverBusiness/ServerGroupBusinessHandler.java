package com.ch.chatWithNetty.serverBusiness;

import com.ch.chatWithNetty.entity.GroupChatEntity;
import com.ch.chatWithNetty.entity.GroupInfo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/8/26 16:34
 * @description: 群聊处理器
 */
public class ServerGroupBusinessHandler extends SimpleChannelInboundHandler<GroupChatEntity> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatEntity msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}
