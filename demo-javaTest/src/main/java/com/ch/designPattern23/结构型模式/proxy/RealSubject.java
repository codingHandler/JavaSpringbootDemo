package com.ch.designPattern23.结构型模式.proxy;

/**
 * @className: RealSubject
 * @Auther: ch
 * @Date: 2022/3/7 17:20
 * @Description: 真实主题
 */
public class RealSubject implements Subject {
    @Override
    public void Request() {
        System.out.println("访问真实主题方法。。。。");
    }
}
