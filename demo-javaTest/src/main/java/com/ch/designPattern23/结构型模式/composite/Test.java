package com.ch.designPattern23.结构型模式.composite;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2021/10/10 10:12
 * @Description: 组合模式
 */
public class Test {
    public static void main(String[] args) {

        // 创建大学
        OrganizationComponent university = new University("北京大学", "很好");
        // 创建学院
        OrganizationComponent colleage1 = new Colleage("信息工程学院", "科学");
        OrganizationComponent colleage2 = new Colleage("护理学院", "医疗");
        // 创建学院下的系(专业)
        colleage1.add(new Department("软件工程","软件"));
        colleage1.add(new Department("网络工程","网络"));
        colleage1.add(new Department("计算机科学与技术","计算机"));

        colleage2.add(new Department("内科","内科描述"));
        colleage2.add(new Department("外科","外科描述"));
        university.add(colleage1);
        university.add(colleage2);
        university.print();
        //colleage1.print();
    }
}
