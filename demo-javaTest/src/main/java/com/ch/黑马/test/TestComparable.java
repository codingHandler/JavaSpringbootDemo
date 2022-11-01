package com.ch.黑马.test;

import com.ch.黑马.algorithm.sort.Student;

/**
 * @author: ch
 * @date: 2022/6/14 21:39
 * @description: TODO
 */
public class TestComparable {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("李四");
        student1.setAge(4);
        Student student2 = new Student();
        student2.setName("张三");
        student2.setAge(23);
        Comparable max = getMax(student1, student2);
        System.out.println(max);
    }

    public static Comparable getMax(Comparable c1, Comparable c2) {
        int i = c1.compareTo(c2);
        if (i >= 0) {
            return c1;
        } else {
            return c2;
        }
    }
}
