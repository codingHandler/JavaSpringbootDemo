package com.ch.Codec.protocol;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

import java.util.UUID;

/**
 * @author: ch
 * @date: 2022/10/26 17:11
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        /*ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>(){
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new ProtobufDecoder(StudentPOJO.Student.getDefaultInstance()));
                ch.pipeline().addLast(new TestHandler());
            }
        };
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(1).setName("张三").build();
        embeddedChannel.writeInbound(student);
*/
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {

                ch.pipeline().addLast(new ProtobufDecoder(ProtoMsg.LoginRequest.getDefaultInstance()));
                ch.pipeline().addLast(new TestHandler());
            }
        };
        ProtoMsg.Message.Builder builder = ProtoMsg.Message.newBuilder();
        builder.setType(ProtoMsg.HeadType.LOGIN_REQUEST)
                .setSessionId(UUID.randomUUID().toString())
                .setSequence(System.currentTimeMillis());
        ProtoMsg.LoginRequest.Builder loginInfo = ProtoMsg.LoginRequest.newBuilder()
                .setUid("100")
                .setAppVersion("1.0")
                .setDeviceId("sss")
                .setPlatform(100)
                .setToken(UUID.randomUUID().toString());
        builder.setLoginRequest(loginInfo);
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        embeddedChannel.writeInbound(builder.build());
    }
}
