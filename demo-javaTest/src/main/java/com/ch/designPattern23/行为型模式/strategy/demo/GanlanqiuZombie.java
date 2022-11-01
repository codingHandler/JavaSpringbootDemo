package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: Ganlanqiu
 * @Auther: ch
 * @Date: 2021/6/11 13:47
 * @Description: 橄榄球僵尸
 */
public class GanlanqiuZombie extends Zombie {
    public GanlanqiuZombie() {
        super.attackable = ()->{
            System.out.println("使用橄榄球");
        };
        moveabel = ()->{
            System.out.println("跑");
        };
    }

    @Override
    void display() {
        System.out.println("会打橄榄球的僵尸");
    }
}
