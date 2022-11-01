package com.ch.designPattern23.创建型模式.factory.simpleMethod;

/**
 * @className: Consumer
 * @Auther: ch
 * @Date: 2021/5/19 15:28
 * @Description: TODO
 */
public class Consumer {
    public static void main(String[] args) {
        Car wulin = new WuLingFactory().getCar();
        wulin.name();
        Car tesila = new TeSiLaFactory().getCar();
        tesila.name();
    }
}
