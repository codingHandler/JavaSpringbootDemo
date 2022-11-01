package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: SimpleZombieMove
 * @Auther: ch
 * @Date: 2021/6/11 13:01
 * @Description: 简单僵尸的移动策略
 */
public class SimpleZombieMove implements MoveAble{
    @Override
    public void move() {
        System.out.println("简单移动方式:走");
    }
}
