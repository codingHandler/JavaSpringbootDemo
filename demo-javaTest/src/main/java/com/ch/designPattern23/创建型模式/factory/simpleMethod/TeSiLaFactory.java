package com.ch.designPattern23.创建型模式.factory.simpleMethod;

/**
 * @className: TeSiLaFactory
 * @Auther: ch
 * @Date: 2021/5/19 15:40
 * @Description: TODO
 */
public class TeSiLaFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new TeSiLa();
    }
}
