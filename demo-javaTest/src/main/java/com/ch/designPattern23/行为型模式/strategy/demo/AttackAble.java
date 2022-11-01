package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: AttackAble
 * @Auther: ch
 * @Date: 2021/6/11 12:51
 * @Description: 僵尸的攻击行为接口
 */
@FunctionalInterface
public interface AttackAble {
    void attack();
}
