package com.ch.nio.selectSplit;

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
        //sc.write(Charset.defaultCharset().encode("hello\nworld\n"));
        //sc.write(Charset.defaultCharset().encode("0123\n456789abcdef"));
        sc.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
        System.out.println("waiting...");


    }
}
