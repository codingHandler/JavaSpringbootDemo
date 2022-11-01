package com.ch.designPattern23.行为型模式.strategy.carDemo;

/**
 * @className: CarStrategy
 * @Auther: ch
 * @Date: 2022/5/18 22:00
 * @Description: 策略模式-汽车使用燃料策略
 */
public interface CarStrategy {
    /**
     * 燃料抽象方法
     */
    void fuel();
}
