package com.ch.designPattern23.结构型模式.bridging.carDemo;

/**
 * @author: ch
 * @date: 2022/5/19 11:19
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        WuLingCar wuLingCar = new WuLingCar(new YouYingQing());
        wuLingCar.driver();
        TeSiLaCar teSiLaCar = new TeSiLaCar(new DianYingQing());
        teSiLaCar.driver();
    }
}
