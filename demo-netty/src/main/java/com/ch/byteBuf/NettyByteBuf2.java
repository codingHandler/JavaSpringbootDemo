package com.ch.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author: ch
 * @date: 2022/8/26 11:24
 * @description: TODO
 */
public class NettyByteBuf2 {
    public static void main(String[] args) {
        // 创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,Netty", CharsetUtil.UTF_8);
        // 获取ByteBuf可读字节数
        int readableBytes = byteBuf.readableBytes();
        for (int i = 0; i < readableBytes; i++) {
            // readByte() 这个方法readIndex会变
            // getByte(index) 这个方法是获取指定索引位置的值
            System.out.println((char) byteBuf.readByte());
        }
        // 获取指定索引段的数据
        CharSequence charSequence = byteBuf.getCharSequence(0, 4, CharsetUtil.UTF_8);
        System.out.println(charSequence);
    }
}
