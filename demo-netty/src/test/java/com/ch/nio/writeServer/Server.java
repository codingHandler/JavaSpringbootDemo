package com.ch.nio.writeServer;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @className: Server
 * @Auther: ch
 * @Date: 2022/4/4 13:59
 * @Description: 服务端向客户端写数据
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 绑定端口
        ssc.bind(new InetSocketAddress(8888));
        // 设置非阻塞模式
        ssc.configureBlocking(false);
        // 创建Selector管理channel
        Selector selector = Selector.open();
        // 将channel注册到Selector中管理,设置事件类型
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 调用select()方法,只有有事件的时候才会继续执行,没有事件则阻塞
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, SelectionKey.OP_READ, null);
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < 30000000; i++) {
                        stringBuffer.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(stringBuffer.toString());
                    // 1,返回实际写入的字节数
                    int write = sc.write(buffer);
                    log.info("实际写入字节:{}", write);
                    // 判断是否有剩余内容
                    if (buffer.hasRemaining()) {
                        scKey.interestOps(scKey.interestOps()|SelectionKey.OP_WRITE);
                        // 把 buffer 作为附件加入 scKey
                        scKey.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    // 获取SocketChannel通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 获取附件
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    if (buffer.hasRemaining()) {
                        int write = socketChannel.write(buffer);
                        log.info("实际写入字节:{}", write);
                        if (!buffer.hasRemaining()) { // 写完了
                            key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);
                            key.attach(null);
                        }
                    }
                }
            }
        }

    }
}
