package com.ch.nio.c4;

import com.ch.nio.c1.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @className: Server
 * @Auther: ch
 * @Date: 2022/4/2 16:04
 * @Description: 使用nio理解阻塞模式
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1,创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2,绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        // 3,连接集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true){
            log.debug("connecting...");
            // 4,accept() 建立与客户端连接, SocketChannel用来与客户端之间的通信
            SocketChannel accept = ssc.accept();  // 阻塞方法,线程停止运行
            log.debug("connected... {}",accept);
            channels.add(accept);
            for (SocketChannel channel : channels) {
                log.debug("before read... {}",channel);
                //5, 接收客户端发送的数据
                channel.read(buffer); //阻塞方法,线程停止运行
                buffer.flip(); // 切到读模式
                ByteBufferUtil.debugAll(buffer);
                buffer.clear(); // 切换回写模式
                log.debug("after read...{}",channel);
            }
        }
    }
}
