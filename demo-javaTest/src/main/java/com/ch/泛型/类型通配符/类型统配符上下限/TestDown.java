package com.ch.泛型.类型通配符.类型统配符上下限;

import java.util.ArrayList;

/**
 * @author: ch
 * @date: 2022/6/12 17:46
 * @description: 类型统配符下限测试
 * 语法: 类/接口<? super 实参类型>
 *     要求该泛型的类型,只能是实参类型,或实参类型的父类类型
 *
 */
public class TestDown {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();
        showAnimal(animals);
        showAnimal(cats);
        //showAnimal(miniCats);

    }
    /**
     * @param list
     * @author ch
     * @date 2022/6/12 17:26
     * @description 类型通配符下限, 要求只能是Cat或者是Cat的父类类型
     **/
    public static void showAnimal(ArrayList<? super Cat> list) {
        // 下限类型通配符可以添加子类类型,但是不报证元素数据类型的约束要求
        // 下限通配符拿的都是Object类型
        list.add(new Cat());
        list.add(new MiniCat());
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            System.out.println(object);
        }
    }
}
