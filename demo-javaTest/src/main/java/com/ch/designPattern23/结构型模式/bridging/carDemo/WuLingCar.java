package com.ch.designPattern23.结构型模式.bridging.carDemo;

/**
 * @author: ch
 * @date: 2022/5/19 11:17
 * @description: 五菱宏光
 */
public class WuLingCar extends BaseCar {

    public WuLingCar(YingQing yingQing) {
        super(yingQing);
    }

    @Override
    public String getBrand() {
        return "五菱宏光";
    }
}
