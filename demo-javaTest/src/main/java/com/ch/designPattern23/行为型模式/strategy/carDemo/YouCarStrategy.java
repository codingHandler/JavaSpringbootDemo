package com.ch.designPattern23.行为型模式.strategy.carDemo;

/**
 * @className: YouCarStrategy
 * @Auther: ch
 * @Date: 2022/5/18 22:03
 * @Description: 油燃料汽车策略
 */
public class YouCarStrategy implements CarStrategy{

    @Override
    public void fuel() {
        System.out.println("使用-油-燃料启动");
    }
}
