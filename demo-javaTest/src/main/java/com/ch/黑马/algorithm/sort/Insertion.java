package com.ch.黑马.algorithm.sort;

import java.util.Arrays;

/**
 * @author: ch
 * @date: 2022/6/20 16:57
 * @description: 插入排序
 */
public class Insertion {

    public static void main(String[] args) {
        Integer[] integers = {4, 6, 8, 7, 9, 2, 10, 1};
        sort3(integers);
        System.out.println(Arrays.toString(integers));
    }

    public static void sort2(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j - 1].compareTo(a[j]) > 0) {
                    Comparable temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
                    break;
                }

            }
        }
    }

    public static void sort3(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j>0; j--) {
                if (a[j].compareTo(a[j-1]) < 0) {
                    Comparable temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    /***
     * 对数组内的元素进行排序
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(a[j - 1], a[j])) {
                    exch(a, j - 1, j);
                }
            }
        }
    }

    /***
     * 判断v是否大于w
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /***
     * 交换a数组中i和j的值
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
