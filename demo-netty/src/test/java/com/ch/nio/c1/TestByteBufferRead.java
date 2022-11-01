package com.ch.nio.c1;

import java.nio.ByteBuffer;

/**
 * @className: TestByteBufferRead
 * @Auther: ch
 * @Date: 2022/3/31 18:00
 * @Description: TODO
 */
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(40);
        buffer.put(new byte[]{'a','b','c','d'});
        buffer.flip();

        // rewind 从头开始读
      /*buffer.get(new byte[4]);
        ByteBufferUtil.debugAll(buffer);
        buffer.rewind();
        System.out.println((char)buffer.get());
        ByteBufferUtil.debugAll(buffer);*/

        // mark & reset
        // mark做一个标记,记录position位置,reset是将position重置到mark的位置
        /*System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.mark();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.reset();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());*/


        // get(i) 不会改变读索引的位置
        System.out.println((char)buffer.get(3));
        ByteBufferUtil.debugAll(buffer);
    }
}
