package com.ch.nio.multiThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

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
        sc.write(Charset.defaultCharset().encode("0123456789abcde"));
        System.out.println("waiting...");


    }
}
