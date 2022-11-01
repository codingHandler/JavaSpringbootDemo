package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: SimpleZombieAttack
 * @Auther: ch
 * @Date: 2021/6/11 12:59
 * @Description: 简单僵尸的攻击策略
 */
public class SimpleZombieAttack implements AttackAble {
    @Override
    public void attack() {
        System.out.println("简单攻击方式:咬");
    }
}
