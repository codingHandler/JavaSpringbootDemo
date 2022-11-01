package com.ch.nio.multiThread;

import com.ch.nio.c1.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @className: MultiThreadServer
 * @Auther: ch
 * @Date: 2022/4/4 20:05
 * @Description: TODO
 */
@Slf4j
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        // 建立服务
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置成非阻塞
        ssc.configureBlocking(false);
        // 绑定端口
        ssc.bind(new InetSocketAddress(8888));
        // 创建Selector
        Selector bossSelector = Selector.open();
        // ServerSocketChannel和selector关联
        ssc.register(bossSelector, SelectionKey.OP_ACCEPT);

        // 创建woker
        Worker worker = new Worker("worker-0");
        while (true) {
            // 调用select()方法,当当没有事件来的时候会阻塞,有事件的时候就会唤醒线程
            bossSelector.select();
            // 获取SelectorKey集合
            Iterator<SelectionKey> iterator = bossSelector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 获取每个SelectionKey对象
                SelectionKey ssckey = iterator.next();
                // 移除SelectionKey对象
                iterator.remove();
                // 判断时候是accept事件
                if (ssckey.isAcceptable()) {
                    // 与客户端建立连接
                    SocketChannel sc = ssc.accept();
                    log.info("connected...{}", sc.getRemoteAddress());
                    sc.configureBlocking(false);
                    log.info("before register...{}", sc.getRemoteAddress());
                    worker.register(sc);
                    log.info("after register...{}", sc.getRemoteAddress());
                }
            }
        }
    }

    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private String name;
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<Runnable>();
        private volatile boolean start = false; // 未初始化

        public Worker(String name) {
            this.name = name;
        }

        // 初始化线程和selector
        public void register(SocketChannel sc) throws IOException {
            if (!start) {
                selector = Selector.open();
                thread = new Thread(this.name);
                thread.start();
                start = true;
                log.info("worker...初始化了");
            }
   /*         queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                    log.info("worker 中Selector...注册了");
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup();*/
            sc.register(selector, SelectionKey.OP_READ);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Runnable task = queue.poll();
                    if (task != null) {
                        log.info("worker中获取了Selector");
                        task.run();
                    }
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey scKey = iterator.next();
                        iterator.remove();
                        if (scKey.isReadable()) {
                            log.info("捕获到了read事件");
                            ByteBuffer buffer = ByteBuffer.allocate(16);
                            SocketChannel channel = (SocketChannel) scKey.channel();
                            log.info("read...{}", channel.getRemoteAddress());
                            channel.read(buffer);
                            buffer.flip();
                            ByteBufferUtil.debugAll(buffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
