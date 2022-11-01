package com.ch.泛型;

import com.ch.泛型.泛型派生子类.ChildFirst;
import com.ch.泛型.泛型派生子类.ChildSecond;

/**
 * @author: ch
 * @date: 2022/6/12 15:47
 * @description: TODO
 */
public class Main {
    public static void main(String[] args) {
        Generic<String> generic1 = new Generic<>("abc");
        String key1 = generic1.getKey();
        System.out.println("key1:"+key1);

        System.out.println("--------------------------------");
        Generic<Integer> generic2 = new Generic<>(12);
        Integer key2 = generic2.getKey();
        System.out.println("key2:"+key2);

    }
}
