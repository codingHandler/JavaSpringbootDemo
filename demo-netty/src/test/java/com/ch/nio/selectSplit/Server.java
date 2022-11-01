package com.ch.nio.selectSplit;

import com.ch.nio.c1.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @className: Server
 * @Auther: ch
 * @Date: 2022/4/4 10:56
 * @Description: TODO
 * <p>
 * selector分为几种类型:
 * accept: 在有连接请求的时候触发
 * connect: 客户端连接建立后触发
 * read: 可读事件
 * write: 可写事件
 */
@Slf4j
public class Server {
    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                ByteBufferUtil.debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }
    public static void main(String[] args) throws IOException {
        // 1,创建selector,管理多个channel,也就是所有事件的集合
        Selector selector = Selector.open();
        // 2,创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置serverSocketChannel为非阻塞模式
        ssc.configureBlocking(false);
        // 3,调用selector和channel的联系(注册)
        // selectionkey就是将来事件发生后,通过他可以知道事件和那个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        // 4,绑定端口
        ssc.bind(new InetSocketAddress(8888));
        while (true) {
            // 5,select()方法,没有事件发生,线程阻塞.有事件,线程才会恢复执行
            // select()方法在事件未处理(accept,cancel)时,他不会阻塞
            selector.select();
            // 6,处理事件,selectKeys()内部包含了所有发生事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                log.info("key: {}", key);
                // 处理key的时候,要冲SelectKeys集合中删除,否则下次处理事件会有问题
                iterator.remove();
                // 区分事件类型
                if (key.isAcceptable()) { // 如果是accept事件
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 7,建立与客户端的连接
                    SocketChannel sc = channel.accept();
                    // 设置SocketChannel非阻塞
                    sc.configureBlocking(false);
                    // 创建Bytebuffer附件
                    ByteBuffer buffer = ByteBuffer.allocate(4);
                    // 8,将与客户端建立的连接通道SocketChannel与selector建立联系,同时添加附件
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    // 9,设置sckey的事件
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("{}", sc);
                    log.info("scKey:{}", scKey);
                } else if (key.isReadable()) { // 如果是read事件
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 获取当前key的附件
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        // 客户端正常关闭会触发一个read事件,read返回值是-1
                        // 如果不处理会进入死循环,所以将这个事件取消调用cancel()方法
                        if (read == -1) {
                            key.cancel();
                        } else {
                            split(buffer);
                            // 如果第一条数据就装满了整个bytebuffer则扩容
                            if (buffer.position()==buffer.limit()){
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        // 因为客户端断开会触发一次read读事件,会导致服务器报IO异常停止服务
                        // 所以需要将key取消(从selector的keys集合中真正删除key)
                        key.cancel();
                    }
                }
            }
        }


    }
}
