package com.ch.designPattern23.结构型模式.adapter.类适配器;

/**
 * @className: ClassAdapterTest
 * @Auther: ch
 * @Date: 2022/5/17 16:37
 * @Description: TODO
 */
public class ClassAdapterTest {
    public static void main(String[] args)
    {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}
