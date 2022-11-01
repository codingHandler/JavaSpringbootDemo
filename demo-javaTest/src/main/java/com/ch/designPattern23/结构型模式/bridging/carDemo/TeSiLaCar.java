package com.ch.designPattern23.结构型模式.bridging.carDemo;

/**
 * @author: ch
 * @date: 2022/5/19 13:39
 * @description: TODO
 */
public class TeSiLaCar extends BaseCar{
    public TeSiLaCar(YingQing yingQing) {
        super(yingQing);
    }

    @Override
    public String getBrand() {
        return "特斯拉";
    }
}
