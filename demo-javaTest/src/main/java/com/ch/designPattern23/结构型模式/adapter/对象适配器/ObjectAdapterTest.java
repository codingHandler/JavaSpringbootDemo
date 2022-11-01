package com.ch.designPattern23.结构型模式.adapter.对象适配器;

import com.ch.designPattern23.结构型模式.adapter.类适配器.Adaptee;
import com.ch.designPattern23.结构型模式.adapter.类适配器.Target;

/**
 * @className: ObjectAdapterTest
 * @Auther: ch
 * @Date: 2022/5/17 16:42
 * @Description: TODO
 */
public class ObjectAdapterTest {
    public static void main(String[] args)
    {
        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
