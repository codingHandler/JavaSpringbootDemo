package com.ch.designPattern23.创建型模式.factory.simpleMethod;

/**
 * @className: WuLingFactory
 * @Auther: ch
 * @Date: 2021/5/19 15:46
 * @Description: TODO
 */
public class WuLingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
