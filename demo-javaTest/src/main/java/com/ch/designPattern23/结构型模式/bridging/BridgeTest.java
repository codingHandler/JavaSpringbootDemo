package com.ch.designPattern23.结构型模式.bridging;

/**
 * @className: BridgeTest
 * @Auther: ch
 * @Date: 2022/5/17 17:01
 * @Description: 桥接模式
 */
public class BridgeTest {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.operation();
    }

}
