package com.ch.nio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author: ch
 * @date: 2022/8/24 11:11
 * @description: TODO
 */
public class NIOClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            //设置非阻塞
            socketChannel.configureBlocking(false);
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            if (!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("正在连接...");
                }
            }
            System.out.println("连接成功!");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // 获取输入内容
                String str = scanner.nextLine();
                // 判断是否是退出指令
                if ("quit".equals(str)) {
                    socketChannel.close();
                    break;
                }
                ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
                socketChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
