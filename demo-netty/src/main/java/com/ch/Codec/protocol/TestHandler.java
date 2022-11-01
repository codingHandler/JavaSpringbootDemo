package com.ch.Codec.protocol;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

/**
 * @author: ch
 * @date: 2022/10/26 17:16
 * @description: TODO
 */
public class TestHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.getClass());
      /*  StudentPOJO.Student student = (StudentPOJO.Student) msg;
        System.out.println("收到数据：id " + student.getId() + " " + student.getName());
        System.out.println(student.toString());
        super.channelRead(ctx, msg);*/
        if (msg instanceof  ProtoMsg.Message ){
            ProtoMsg.Message pkg = (ProtoMsg.Message) msg;
            ProtoMsg.HeadType headType = pkg.getType();
            System.out.println("类型： "+pkg.getType());
            if (!headType.equals(ProtoMsg.HeadType.LOGIN_REQUEST)) {
                super.channelRead(ctx, msg);
                return;
            }
            ProtoMsg.LoginRequest loginRequest = pkg.getLoginRequest();
            System.out.println(loginRequest.toString());
        }
    }
}
