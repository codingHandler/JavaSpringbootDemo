package com.ch.designPattern23.创建型模式.factory.abstractFactory;

/**
 * @className: Client
 * @Auther: ch
 * @Date: 2021/5/19 16:25
 * @Description: TODO
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("============小米==========");
        IPhoneProduct iPhoneProduct = new XiaomiFactory().iphoneProduct();
        iPhoneProduct.callup();
        iPhoneProduct.sendSMS();

        System.out.println("============华为==========");
        IPhoneProduct iPhoneProduct1 = new HuaweiFactory().iphoneProduct();
        iPhoneProduct1.callup();
        iPhoneProduct1.sendSMS();
    }
}
