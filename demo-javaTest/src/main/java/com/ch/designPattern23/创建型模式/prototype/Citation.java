package com.ch.designPattern23.创建型模式.prototype;

/**
 * @className: Prototype
 * @Auther: ch
 * @Date: 2022/3/7 16:31
 * @Description: 原型模式引用对象
 */
public class Citation implements Cloneable {
    String name;
    String info;
    String college;

    Citation() {
    }


    Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功！");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return (this.name);
    }

    public void display() {
        System.out.println(name + info + college);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功！");
        return (Citation) super.clone();
    }
}
