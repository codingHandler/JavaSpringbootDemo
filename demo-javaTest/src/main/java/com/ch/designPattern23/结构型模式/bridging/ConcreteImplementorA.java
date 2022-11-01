package com.ch.designPattern23.结构型模式.bridging;

/**
 * @className: ConcreteImplementorA
 * @Auther: ch
 * @Date: 2022/5/17 16:58
 * @Description: 具体实现化角色
 */
public class ConcreteImplementorA implements Implementor{
    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}
