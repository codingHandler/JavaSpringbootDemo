package com.ch.designPattern23.行为型模式.template;

/**
 * @className: ConcreteClass
 * @Auther: ch
 * @Date: 2022/3/7 17:47
 * @Description: 具体实现类
 */
public class ConcreteClass extends AbstractClass {
    @Override
    void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用了》》》");
    }

    @Override
    void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用了》》》");
    }
}
