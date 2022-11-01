package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: XiaomiFactory
 * @Auther: ch
 * @Date: 2021/5/19 16:20
 * @Description: TODO
 */
public class XiaomiFactory implements IProductFactory {
    @Override
    public IPhoneProduct iphoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new XiaomiRouter();
    }
}
