package com.ch.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ch
 * @date: 2022/8/23 11:32
 * @description: TODO
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 线程池机制
        // 思路
        // 1,创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 2,如果有客户端连接,就创建一个线程与之通讯(单独写一个方法)
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了...");

        while (true) {
            System.out.println("等待连接...");
            // 监听,等待客户端连接
            Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.submit(() -> {
                handler(accept);
            });
        }
    }

    // 编写一个handler方法,和客户端通讯
    private static void handler(Socket socket) {
        try {
            System.out.println("线程信息id:" + Thread.currentThread().getId() + " 名字:" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            // 循环读取客户端发送过来的数据
            while (true) {
                System.out.println("等待...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输出客户端发送过来的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
