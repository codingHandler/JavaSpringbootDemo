package com.ch.designPattern23.结构型模式.bridging;

import javax.swing.*;

/**
 * @className: Abstraction
 * @Auther: ch
 * @Date: 2022/5/17 16:54
 * @Description: 抽象化角色
 */
public abstract class Abstraction {
    protected Implementor imple;

    protected Abstraction(Implementor imple){
        this.imple = imple;
    }

    public abstract void operation();
}
