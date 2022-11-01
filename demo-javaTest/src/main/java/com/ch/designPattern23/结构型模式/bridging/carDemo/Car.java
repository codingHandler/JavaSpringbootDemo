package com.ch.designPattern23.结构型模式.bridging.carDemo;

/**
 * @className: Car
 * @Auther: ch
 * @Date: 2022/5/19 10:59
 * @Description: 抽象Car对象
 */
public abstract class Car {
    protected YingQing yingQing;

    public Car(YingQing yingQing) {
        this.yingQing = yingQing;
    }

    public abstract void driver();
}
