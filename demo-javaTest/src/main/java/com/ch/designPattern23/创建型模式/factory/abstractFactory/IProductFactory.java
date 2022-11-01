package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: IProductFactory
 * @Auther: ch
 * @Date: 2021/5/19 16:17
 * @Description: 抽象产品工厂
 */
public interface IProductFactory {

    // 生产手机
    IPhoneProduct iphoneProduct();
    // 生产路由器
    IRouterProduct routerProduct();

}
