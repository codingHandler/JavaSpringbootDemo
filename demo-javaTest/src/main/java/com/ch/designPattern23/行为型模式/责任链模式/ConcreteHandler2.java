package com.ch.designPattern23.行为型模式.责任链模式;

/**
 * @className: ConcreteHandler2
 * @Auther: ch
 * @Date: 2022/3/8 14:03
 * @Description: 具体处理者角色2
 */
public class ConcreteHandler2 extends Handler {

    @Override
    public void handleRequest(String request) {
        if (request.equals("two")) {
            System.out.println("具体处理者2负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}
