package com.ch.designPattern23.行为型模式.观察者模式;

/**
 * @author: ch
 * @date: 2022/9/19 14:14
 * @description: TODO
 */
public interface Subject {
    /***
     * 增加订阅者
     * @param observer
     */
    public void addObs(Observer observer);

    /***
     * 删除订阅者
     * @param observer
     */
    public void delObs(Observer observer);

    /***
     * 通知订阅者更新消息
     * @param message
     */
    public void notify(String message);
}
