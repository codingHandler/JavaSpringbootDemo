package com.ch.protocol.自定义协议.protocol;

import com.ch.protocol.自定义协议.message.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;
import sun.awt.EmbeddedFrame;

/**
 * @author: ch
 * @date: 2022/8/31 20:54
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) throws Exception {
        EmbeddedChannel embeddedChannel = new EmbeddedChannel(new LoggingHandler(), new MessageCodec());
        // encode
        LoginRequestMessage zhangsan = new LoginRequestMessage("zhangsan", "123456");
        embeddedChannel.writeOutbound(zhangsan);

        //decode
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null,zhangsan,buffer);
        // 入站
        embeddedChannel.writeInbound(buffer);

    }
}
