package com.ch.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @className: TestByteBufferReadWrite
 * @Auther: ch
 * @Date: 2022/3/31 16:50
 * @Description: 工具类查看byteBuffer指针
 */
@Slf4j
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x62,0x63,0x64});
        ByteBufferUtil.debugAll(buffer);
        // 不切换为读模式直接读取,从当前的position指针出读取值
        // log.info(String.valueOf((char)buffer.get()));
        buffer.flip();// 切换为读模式,position指针重置为0
        log.info(String.valueOf((char)buffer.get()));
        buffer.compact(); // 切换回写模式
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x65,0x6f});
        ByteBufferUtil.debugAll(buffer);

    }
}
