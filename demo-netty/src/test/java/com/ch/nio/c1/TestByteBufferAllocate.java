package com.ch.nio.c1;

import java.nio.ByteBuffer;

/**
 * @className: TestByteBufferAllocate
 * @Auther: ch
 * @Date: 2022/3/31 17:50
 * @Description: TODO
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());
        /*
        * class java.nio.HeapByteBuffer    -java堆内存,读写效率低,受到垃圾回收GC的影响
        * class java.nio.DirectByteBuffer  -直接内存,读写效率高(少一次拷贝),不会受GC影响,分配的效率低
        * */
    }
}
