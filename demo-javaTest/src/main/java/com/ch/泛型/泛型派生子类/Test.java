package com.ch.泛型.泛型派生子类;

import com.ch.泛型.泛型派生子类.ChildFirst;
import com.ch.泛型.泛型派生子类.ChildSecond;

/**
 * @author: ch
 * @date: 2022/6/12 16:15
 * @description: TODO
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("---------------泛型派生类1------------------");
        ChildFirst<String> stringChildFirst = new ChildFirst<>();
        String value1 = stringChildFirst.getValue();
        System.out.println(value1);

        System.out.println("---------------泛型派生类2------------------");
        ChildSecond childSecond = new ChildSecond();
        childSecond.setValue(100);
        Integer value2 = childSecond.getValue();
        System.out.println(value2);
    }
}
