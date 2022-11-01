package com.ch.nio.c1;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @className: TestByteBufferString
 * @Auther: ch
 * @Date: 2022/4/2 14:30
 * @Description: TODO
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        // 1,字符串转ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        ByteBufferUtil.debugAll(buffer);

        // Charset:
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(buffer2);

        // wrap
        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        ByteBufferUtil.debugAll(wrap);

    }
}
