package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: HuaweiFactory
 * @Auther: ch
 * @Date: 2021/5/19 16:22
 * @Description: 华为工厂
 */
public class HuaweiFactory implements IProductFactory {
    @Override
    public IPhoneProduct iphoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new HuaweiRouter();
    }
}
