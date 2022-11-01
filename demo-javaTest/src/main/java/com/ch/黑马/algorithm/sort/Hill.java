package com.ch.黑马.algorithm.sort;

import java.util.Arrays;

/**
 * @author: ch
 * @date: 2022/7/12 13:46
 * @description: 希尔排序
 */
public class Hill {
    public static void main(String[] args) {
        Integer[] integers = {4, 6, 8, 7, 9, 2, 10, 1};
        //sort(integers);
        sort(integers);
        System.out.println(Arrays.toString(integers));
    }

    private static void sort(Comparable[] comparables) {
        // 1,根据数组a的长度,确认增长量h的初始值
        int h = 1;
        while (h < comparables.length / 2) {
            h = 2 * h + 1;
        }
        // 2,希尔排序
        while (h >= 1) {
            // 排序
            // 2.1找到待插入元素
            for (int i = h; i < comparables.length; i++) {
                // 2.2把待插入元素插入到有序数列中
                for (int j = i; j >= h; j -= h) {
                    // 待插入的元素是a[j],比较a[j]和a[j-h]
                    if (greater(comparables[j - h], comparables[j])) {
                        // 交换元素
                        exChange(comparables, j, j - h);
                    } else {
                        // 待插入的元素已经找到了合适的位置,结束循环
                        break;
                    }
                }
            }
            h /= 2;
        }

    }

    /***
     * 比较v是否大于w
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /***
     * 交换元素i和元素j的位置
     * @param comparables
     * @param i
     * @param j
     */
    private static void exChange(Comparable[] comparables, int i, int j) {
        Comparable temp = comparables[i];
        comparables[i] = comparables[j];
        comparables[j] = temp;
    }
}
