package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: IRouterProduct
 * @Auther: ch
 * @Date: 2021/5/19 16:09
 * @Description: 路由器产品接口
 */
public interface IRouterProduct {
    void start();
    void shutdown();
    void openwifi();
    void setting();
}
