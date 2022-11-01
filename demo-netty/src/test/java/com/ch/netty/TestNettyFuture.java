package com.ch.netty;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @className: TestNettyFuture
 * @Auther: ch
 * @Date: 2022/4/6 15:32
 * @Description: TODO
 */
@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.info("执行计算");
                Thread.sleep(2000);
                return 70;
            }
        });
        log.info("等待结果...");
        // 1,同步阻塞获取结果
        log.info("结果是: {}",future.get());
        // 2,异步获取结果
      /*  future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.info("结果是: {}",future.getNow());
            }
        });*/
        log.info("主线程执行完毕...");

    }
}
