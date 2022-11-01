package com.ch.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @className: Client
 * @Auther: ch
 * @Date: 2022/4/2 16:29
 * @Description: TODO
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8888));
        System.out.println("waiting...");


    }
}
