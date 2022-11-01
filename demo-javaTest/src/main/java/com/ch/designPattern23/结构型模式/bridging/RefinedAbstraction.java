package com.ch.designPattern23.结构型模式.bridging;

/**
 * @className: RefinedAbstraction
 * @Auther: ch
 * @Date: 2022/5/17 16:58
 * @Description: 扩展抽象化角色
 */
public class RefinedAbstraction extends Abstraction{

    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();
    }
}
