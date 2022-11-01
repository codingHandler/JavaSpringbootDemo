package com.ch.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: ch
 * @date: 2022/8/24 10:42
 * @description: 服务端
 */
public class NIOServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        try {
            // 创建ServerSocketChannel 相当于ServerSocket
            serverSocketChannel = ServerSocketChannel.open();
            //将 ServerSocketChannel 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 绑定端口6666,在服务器端监听
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            // 创建Selector
            Selector selector = Selector.open();
            // 把serverSocketChannel注册到selector中,关心的事件为OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("注册后的selectionkey 数量:" + selector.keys().size());

            // 循环等待客户端连接
            while (true) {
                if (selector.select(1000) == 0) {
                    System.out.println("无事件发生,服务端等待1秒");
                    System.out.println("当前连接数为:" + selector.keys().size());
                    continue;
                }
                // selectedKeys() 返回关注事件集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("selectionKeys 数量 = " + selectionKeys.size());

                //遍历 Set<SelectionKey>, 使用迭代器遍历
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    // 手动从集合中移除当前selectionKey,防止重复操作
                    keyIterator.remove();
                    //根据key 对应的通道发生的事件做相应处理
                    if (key.isAcceptable()) {
                        //该该客户端生成一个 SocketChannel
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        System.out.println("客户端连接成功 生成了一个 socketChannel " + socketChannel.hashCode());
                        //将 SocketChannel 设置为非阻塞
                        socketChannel.configureBlocking(false);
                        // 将socketChannel注册到selector,关注事件为OP_READ,同时给socketChannel关联一个Buffer
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("客户端连接后,注册的selectionkey数量: " + selector.keys().size());
                    }

                    if (key.isReadable()) {
                        SocketChannel socketChannel = null;
                        try {
                            // 获取通道
                            socketChannel = (SocketChannel) key.channel();
                            // 获取ByteBuffer
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            // 将通道中的数据读取到ByteBuffer
                            int read = socketChannel.read(buffer);
                            // ！！！注意这里,如果没有此判断,客户端异常/正常关闭,会出现服务端关闭,或者服务端循环读问题
                            if (read == -1) {
                                socketChannel.close();
                                key.cancel();
                                System.out.println("客户端关闭了.");
                                continue;
                            }
                            System.out.println("来自 客户端消息: " + new String(buffer.array()));
                        } catch (IOException e) {
                            socketChannel.close();
                            System.out.println("客户端关闭了.");
                            e.printStackTrace();
                        }
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
