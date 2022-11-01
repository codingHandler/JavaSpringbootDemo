package com.ch.designPattern23.行为型模式.strategy.demo;

/**
 * @className: Zombie
 * @Auther: ch
 * @Date: 2021/6/11 12:50
 * @Description: 僵尸的基类
 */
public abstract class Zombie {
    // 僵尸的移动行为和攻击行为
    MoveAble moveabel;
    AttackAble attackable;

    // 僵尸的样子
    abstract void display();

    // 僵尸的移动行为
    public void move() {
        moveabel.move();
    }

    // 僵尸的攻击行为
    public void attack() {
        attackable.attack();
    }

    public MoveAble getMoveabel() {
        return moveabel;
    }

    public void setMoveabel(MoveAble moveabel) {
        this.moveabel = moveabel;
    }

    public AttackAble getAttackable() {
        return attackable;
    }

    public void setAttackable(AttackAble attackable) {
        this.attackable = attackable;
    }
}
