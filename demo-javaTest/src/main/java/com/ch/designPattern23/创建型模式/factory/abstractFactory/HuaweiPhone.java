package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: XimiPhone
 * @Auther: ch
 * @Date: 2021/5/19 16:11
 * @Description: 华为手机
 */
public class HuaweiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void callup() {
        System.out.println("华为手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为手机发短信");
    }
}
