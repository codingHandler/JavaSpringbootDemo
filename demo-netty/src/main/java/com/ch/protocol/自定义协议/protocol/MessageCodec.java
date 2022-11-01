package com.ch.protocol.自定义协议.protocol;

import com.ch.protocol.自定义协议.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/8/31 17:53
 * @description: 自定义编解码
 */
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // 1, 3字节魔术
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 2, 1字节版本号
        out.writeByte(1);
        // 3, 1字节的序列化方式 jdk 0 ,json 1
        out.writeByte(0);
        // 4, 1字节指令类型
        out.writeByte(msg.getMessageType());
        // 5, 4字节请求序号,为了双工通信,提供异步能力
        out.writeInt(msg.getSequenceId());
        // 获取内容的字节数组
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();
        // 6, 正文长度
        out.writeInt(bytes.length);
        // 现在固定字节长度为14,此字节无意义,用于对其填充,将固定字节数数满2的n次方倍16
        out.writeByte(0xff);
        // 7, 消息正文
        out.writeBytes(bytes);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializedType = in.readByte();
        byte commandType = in.readByte();
        int sequenceId = in.readInt();
        int contentLength = in.readInt();
        int a = in.readByte();
        byte[] contentBytes = new byte[contentLength];
        // 将内容读到contentBytes字节数组中
        in.readBytes(contentBytes, 0, contentLength);
        Message message = null;
        if (serializedType == 0) {
            ObjectInputStream ois =
                    new ObjectInputStream(new ByteArrayInputStream(contentBytes));
            message = (Message) ois.readObject();
        }
        System.out.println("magicNum: " + magicNum);
        System.out.println("version: " + version);
        System.out.println("serializedType: " + serializedType);
        System.out.println("commandType: " + commandType);
        System.out.println("sequenceId: " + sequenceId);
        System.out.println("a: " + a);
        System.out.println("message: " + message.toString());
        if (message != null) {
            out.add(message);
        }

    }
}
