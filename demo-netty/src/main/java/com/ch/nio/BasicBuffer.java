package com.ch.nio;

import java.nio.IntBuffer;

/**
 * @author: ch
 * @date: 2022/8/23 14:25
 * @description: 简单使用ByteBuffer
 *
 * - BIO以流的方式处理数据,NIO以块的方式处理数据,NIO的效率比BIO效率高
 * - BIO是阻塞的,NIO是非阻塞的
 * - BIO基于字节流和字符流进行操作,而NIO基于Channel(通道)和Buffer(缓冲区)进行操作,数据总是从通道读取到缓冲区中
 *   或者从缓存区中写入到通道中
 *   Select(选择器)用于监听多个通道的时间(比如:连接请求,数据到达等),因此使用单个线程就可以监听多个客户端通道
 * - Selector,Channel,Buffer的关系:
 *     1),每个channel都对应一个Buffer
 *     2),Selector对应一个线程,一个线程对应多个Channel(可以理解为一个channel为一个连接)
 *     3),Selector会根据不同的事件,在各个通道上切换
 *     4),Buffer就是一个内存块,底层是一个数组
 *     5),数据的读取写入是通过Buffer,这个和BIO不同,BIO中要么是输入流,或者是输出流,不能双向.
 *        但是NIO的Buffer是可以读也可以写,需要flip方法切换
 *     6),channel是双向的,可以返回底层操作系统的情况,比如Linux,底层操作系统通道就是双向的
 *
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // 创建一个Buffer,大小为5,既可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        // 向IntBuffer中存入5个int类型数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        // 将buffer转换为读模式
        intBuffer.flip();
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
