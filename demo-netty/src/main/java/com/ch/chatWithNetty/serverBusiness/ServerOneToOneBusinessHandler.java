package com.ch.chatWithNetty.serverBusiness;

import com.ch.chatWithNetty.entity.OneToOneChatEntity;
import com.ch.chatWithNetty.utils.DataBasesUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashMap;


/**
 * @author: ch
 * @date: 2022/8/26 15:53
 * @description: 单聊处理器
 */
public class ServerOneToOneBusinessHandler extends SimpleChannelInboundHandler<OneToOneChatEntity> {

    private HashMap<String, Channel> userChannelMap = DataBasesUtils.userChannelMap;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, OneToOneChatEntity msg) throws Exception {
        // 判断此通道是否存在
        if (userChannelMap.containsKey(msg.getToUser())) {
            Channel channel = userChannelMap.get(msg.getToUser().getUsername());
            channel.writeAndFlush(msg.getMsg());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

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
