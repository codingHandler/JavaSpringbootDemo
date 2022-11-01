package com.ch.designPattern23.结构型模式.proxy;

/**
 * @className: Proxy
 * @Auther: ch
 * @Date: 2022/3/7 17:21
 * @Description: 代理
 */
public class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void Request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }
    private void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }
    private void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }

}
