package com.ch.designPattern23.行为型模式.责任链模式;

/**
 * @className: ChainOfResponsibilityPattern
 * @Auther: ch
 * @Date: 2022/3/8 14:04
 * @Description: 责任链模式测试
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("two");
    }
}
