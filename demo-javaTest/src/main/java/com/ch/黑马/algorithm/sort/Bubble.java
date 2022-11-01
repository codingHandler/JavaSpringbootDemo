package com.ch.黑马.algorithm.sort;

import java.util.Arrays;

/**
 * @author: ch
 * @date: 2022/6/15 13:01
 * @description: 冒泡排序
 */
public class Bubble {

    public static void main(String[] args) {
        Integer[] integers = {4, 6, 8, 7, 9, 2, 10, 1};
        //sort(integers);
        sort2(integers);
        System.out.println(Arrays.toString(integers));
    }


    private static void sort2(Comparable[] comparables) {
        for (int i = 0; i < comparables.length-1; i++) {
            for (int j = i + 1; j < comparables.length; j++) {
                if (comparables[i].compareTo(comparables[j]) > 0) {
                    Comparable temp = comparables[i];
                    comparables[i] = comparables[j];
                    comparables[j] = temp;
                }
            }
        }
    }


    /***
     * 对数组中compareables中的元素进行排序
     * */
    private static void sort(Comparable[] comparables) {

        for (int i = comparables.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (greater(comparables[j], comparables[j + 1])) {
                    exChange(comparables, j, j + 1);
                }
            }
        }

    }

    /***
     * 比较元素v是否大于w元素
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /***
     * 数组元素i和j交换位置
     * @param a
     * @param i
     * @param j
     */
    private static void exChange(Comparable[] a, int i, int j) {
        Comparable temp;
        temp = a[j];
        a[j] = a[i];
        a[i] = temp;

    }


}
