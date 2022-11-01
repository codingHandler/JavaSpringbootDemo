package com.ch.designPattern23.创建型模式.factory.simple;

/**
 * @className: Consumer
 * @Auther: ch
 * @Date: 2021/5/19 15:28
 * @Description: 客户
 */
public class Consumer {
    public static void main(String[] args) {
        Car wulin = CarFactory.getCar("五菱");
        wulin.getName();
        Car tesila = CarFactory.getCar("特斯拉");
        tesila.getName();
    }
}
