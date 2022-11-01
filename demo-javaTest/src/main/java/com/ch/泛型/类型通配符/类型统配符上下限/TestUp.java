package com.ch.泛型.类型通配符.类型统配符上下限;

import java.util.ArrayList;

/**
 * @author: ch
 * @date: 2022/6/12 17:21
 * @description: 泛型上限统配符测试类
 */
public class TestUp {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();
        //showAnimal(animals);
        showAnimal(cats);
        showAnimal(miniCats);

    }

    /**
     * @param list
     * @author ch
     * @date 2022/6/12 17:26
     * @description 泛型上限通配符, 传递的集合类型, 只能是Cat和Cat的子类
     **/
    public static void showAnimal(ArrayList<? extends Cat> list) {
        // 使用了泛型上限类型通配符,不可以在往集合中添加元素
        // list.add(new Cat());

        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
            System.out.println(cat);
        }
    }
}
