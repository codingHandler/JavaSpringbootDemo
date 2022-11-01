package com.ch.designPattern23.行为型模式.责任链模式;

/**
 * @className: Handler
 * @Auther: ch
 * @Date: 2022/3/8 14:02
 * @Description: 抽象处理者模式
 */
public abstract class Handler {
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }
    public Handler getNext() {
        return next;
    }
    //处理请求的方法
    public abstract void handleRequest(String request);
}
