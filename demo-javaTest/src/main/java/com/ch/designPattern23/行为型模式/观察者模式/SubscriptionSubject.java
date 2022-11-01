package com.ch.designPattern23.行为型模式.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ch
 * @date: 2022/9/19 14:17
 * @description: TODO
 */
public class SubscriptionSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObs(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void delObs(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
