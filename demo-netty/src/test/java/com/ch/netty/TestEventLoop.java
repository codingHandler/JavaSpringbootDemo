package com.ch.netty;

import io.netty.channel.DefaultEventLoopGroup;

/**
 * @className: TestEventLoop
 * @Auther: ch
 * @Date: 2022/4/6 11:06
 * @Description: 测试EventLoop
 */
public class TestEventLoop {
    public static void main(String[] args) {
        // EventLoopGroup是一组EventLoop,Channel一般会调用EventLoopGroup的register方法来绑定其中一个EventLoop
        DefaultEventLoopGroup group = new DefaultEventLoopGroup(2);
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());

    }
}
