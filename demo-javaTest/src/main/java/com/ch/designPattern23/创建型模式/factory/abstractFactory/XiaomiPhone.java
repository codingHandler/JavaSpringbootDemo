package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: XimiPhone
 * @Auther: ch
 * @Date: 2021/5/19 16:11
 * @Description: 小米手机
 */
public class XiaomiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void callup() {
        System.out.println("小米手机打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("小米手机发短信");
    }
}
