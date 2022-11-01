package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: XiaomiRouter
 * @Auther: ch
 * @Date: 2021/5/19 16:12
 * @Description: 小米路由器
 */
public class XiaomiRouter implements IRouterProduct {
    @Override
    public void start() {
        System.out.println("开启小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    @Override
    public void openwifi() {
        System.out.println("开启小米路由器wifi");
    }

    @Override
    public void setting() {
        System.out.println("设置小米路由器");
    }
}
