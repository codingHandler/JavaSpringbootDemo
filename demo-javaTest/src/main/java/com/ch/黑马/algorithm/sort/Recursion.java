package com.ch.黑马.algorithm.sort;

/**
 * @author: ch
 * @date: 2022/7/13 13:04
 * @description: 使用递归方式实现一个数的阶乘
 */
public class Recursion {

    public static void main(String[] args) {
        int n  =5;
        long res = factorialMethod(n);
        System.out.println(n+"的阶乘结果为: "+res);
    }

    /***
     * 阶乘方法
     * @param n
     */
    private static long factorialMethod(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorialMethod(n - 1);
    }


}
