package com.ch.designPattern23.行为型模式.观察者模式;

/**
 * @author: ch
 * @date: 2022/9/19 14:19
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        SubscriptionSubject subscriptionSubject = new SubscriptionSubject();
        subscriptionSubject.addObs(new WeixingObserver("小明"));
        subscriptionSubject.addObs(new WeixingObserver("小猪"));
        subscriptionSubject.addObs(new WeixingObserver("小华"));
        subscriptionSubject.notify("出来做核酸了");
    }
}
