package com.ch.黑马.algorithm.sort;

import org.checkerframework.checker.units.qual.C;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * @author: ch
 * @date: 2022/6/15 15:40
 * @description: 选择排序
 */
public class Selection {

    public static void main(String[] args) {
        Integer[] integers = {4, 6, 8, 7, 9, 2, 10, 1};
        sort2(integers);
        System.out.println(Arrays.toString(integers));
    }

    /***
     * 选择排序总结:
     *  与冒泡排序的区别在于,只需要交换一次数组元素位置
     * @param comparables
     */
    private static void sort2(Comparable[] comparables){
        for (int i = 0; i < comparables.length; i++) {
            int minIndex = i;
            for (int j = i+1;j<comparables.length;j++){
                if (comparables[minIndex].compareTo(comparables[j])>0){
                    minIndex = j;
                }
            }
            Comparable temp = comparables[i];
            comparables[i] = comparables[minIndex];
            comparables[minIndex] = temp;
        }
    }

    private static void sort(Comparable[] comparables) {
        for (int i = 0; i <= comparables.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < comparables.length; j++) {
                if (greater(comparables[minIndex], comparables[j])) {
                    minIndex = j;
                }
            }
            exChange(comparables, i, minIndex);
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
        Comparable temp;
        temp = comparables[i];
        comparables[i] = comparables[j];
        comparables[j] = temp;
    }

}
