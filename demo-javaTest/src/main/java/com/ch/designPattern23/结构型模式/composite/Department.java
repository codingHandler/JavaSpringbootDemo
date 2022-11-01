package com.ch.designPattern23.结构型模式.composite;

/**
 * @className: Department
 * @Auther: ch
 * @Date: 2021/10/10 10:15
 * @Description: 组合模式-系
 */
public class Department extends OrganizationComponent {
    public Department(String name, String desc) {
        super(name, desc);
    }

    // add,remove方法不用重写,因为他是叶子节点
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    @Override
    public void print() {
        System.out.println(getDesc());
    }
}
