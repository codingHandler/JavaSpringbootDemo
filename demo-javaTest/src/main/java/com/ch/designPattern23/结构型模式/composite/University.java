package com.ch.designPattern23.结构型模式.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: University
 * @Auther: ch
 * @Date: 2021/10/10 10:04
 * @Description: 组合模式-大学
 */
public class University extends OrganizationComponent {

    List<OrganizationComponent> list = new ArrayList<>();

    //构造器
    public University(String name, String desc) {
        super(name, desc);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        list.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        list.remove(organizationComponent);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDesc() {
        return super.getDesc();
    }

    // print方法,输出University包含的学院
    @Override
    public void print() {
        System.out.println("----------" + getName() + "----------");
        for (OrganizationComponent item : list) {
            item.print();
        }
    }
}
