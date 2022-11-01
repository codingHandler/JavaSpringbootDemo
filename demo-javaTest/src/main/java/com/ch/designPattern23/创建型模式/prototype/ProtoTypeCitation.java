package com.ch.designPattern23.创建型模式.prototype;

/**
 * @className: ProtoTypeCitation
 * @Auther: ch
 * @Date: 2022/3/7 16:40
 * @Description: 原型模式
 * <p>
 * 原型模式的克隆分为浅克隆和深克隆。
 * 浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
 * 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 */
public class ProtoTypeCitation {
    public static void main(String[] args) throws CloneNotSupportedException {
        Citation obj1 = new Citation("张三",
                "同学：在2021学年第一学期中表现优秀，被评为三好学生。",
                "韶关学院");
        obj1.display();
        Citation obj2 = (Citation) obj1.clone();
        obj2.setName("李四");
        obj2.display();
    }
}
