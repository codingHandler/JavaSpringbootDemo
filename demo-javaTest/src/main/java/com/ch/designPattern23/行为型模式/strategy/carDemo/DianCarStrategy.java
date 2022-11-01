package com.ch.designPattern23.行为型模式.strategy.carDemo;

/**
 * @className: DianCar
 * @Auther: ch
 * @Date: 2022/5/18 22:01
 * @Description: 电燃料汽车策略
 */
public class DianCarStrategy implements CarStrategy{
    @Override
    public void fuel() {
        System.out.println("使用-电-燃料启动");
    }
}
