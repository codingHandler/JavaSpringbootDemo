package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: BigHeadZombie
 * @Auther: ch
 * @Date: 2021/6/11 13:24
 * @Description: 大头僵尸
 */
public class BigHeadZombie extends Zombie {
    public BigHeadZombie(){
        // 使用lambda表达式
        attackable = () -> { System.out.println("用头撞"); };
        // 使用匿名内部类方式
        moveabel = new MoveAble() {
            @Override
            public void move() {
                System.out.println("慢慢走,速度很慢");
            }
        };
    }
    @Override
    void display() {
        System.out.println("我是大头僵尸");
    }
}
