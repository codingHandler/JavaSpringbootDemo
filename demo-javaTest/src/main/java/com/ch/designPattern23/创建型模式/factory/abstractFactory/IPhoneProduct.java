package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: IPhoneProduct
 * @Auther: ch
 * @Date: 2021/5/19 16:07
 * @Description: 手机产品接口
 */
public interface IPhoneProduct {
    void start();
    void shutdown();
    void callup();
    void sendSMS();
}
