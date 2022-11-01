package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: SimpleZombie
 * @Auther: ch
 * @Date: 2021/6/11 13:02
 * @Description: 普通僵尸
 */
public class SimpleZombie extends Zombie {

    public SimpleZombie() {
        // 给简单僵尸赋予默认行为
        moveabel = new SimpleZombieMove();
        attackable = new SimpleZombieAttack();
    }

    @Override
    void display() {
        System.out.println("我是普通僵尸");
    }
}
