package com.ch.designPattern23.行为型模式.责任链模式;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2022/3/8 16:11
 * @Description: TODO
 */
public class Test {
    public static void main(String[] args) {
        String A = "a";
        String B = "b";
        swapValue(A, B);
        System.out.println("A :" + A);
        System.out.println("B :" + B);
    }

    private static void swapValue(String a, String b) {
        String c = a;
        a = b;
        b = c;
        System.out.println("方法内a :"+a);
        System.out.println("方法内b :"+b);
    }
}
