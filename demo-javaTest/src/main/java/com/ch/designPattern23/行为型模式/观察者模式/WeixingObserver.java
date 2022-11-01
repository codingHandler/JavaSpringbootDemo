package com.ch.designPattern23.行为型模式.观察者模式;

/**
 * @author: ch
 * @date: 2022/9/19 14:16
 * @description: TODO
 */
public class WeixingObserver implements Observer {

    private String name;

    public WeixingObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name+ ">>" +message);
    }
}
