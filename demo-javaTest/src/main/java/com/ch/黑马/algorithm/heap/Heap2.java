package com.ch.黑马.algorithm.heap;

/**
 * @author: ch
 * @date: 2022/7/27 09:40
 * @description: 自定义实现堆
 */
public class Heap2<T extends Comparable> {
    private T[] heaps;
    private Integer size;

    public Heap2(Integer capacity) {
        this.heaps = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    /***
     * 往堆中添加元素
     * @param t
     */
    public void insert(T t) {
        this.heaps[++size] = t;
        swim(size);
    }

    /***
     * 上浮
     */
    private void swim(Integer k) {
        // 如果当前节点不是根节点,则进入上浮逻辑
        while (k > 1) {
            // 如果根节点小于当前节点,就进行
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    /***
     * 删除最大元素
     * @return
     */
    public T delMax() {
        // 判断堆是否只有一个元素
        if (this.size == 1) {
            T node = this.heaps[1];
            this.heaps[1] = null;
            return node;
        } else if (this.size > 1) {
            // 获取最大节点
            T node = this.heaps[1];
            // 交换当前节点和最左接节点
            exch(1, size);
            // 将最左节点值重置为null,同时size--
            this.heaps[size--] = null;
            // 根节点进行下沉操作
            sink(1);
            // 返回最大值
            return node;
        }
        return null;
    }

    /***
     * 指定k索引处节点进行下沉操作
     * @param k
     */
    private void sink(Integer k) {
        while (2 * k <= this.size) {
            // 记录左右子节点中的最大节点索引
            int max;
            // 判断是否有右子节点,如果有右子节点,则需要比较左子节点和右子节点的大小,取较大的索引
            if (2 * k + 1 <= this.size) {
                // 判断右字节点是否小于左子节点,是则使用左子节点的索引为max,否则使用右子节点的索引为max
                if (less(2 * k + 1, 2 * k)) {
                    max = 2 * k;
                } else {
                    max = 2 * k + 1;
                }
            } else {
                // 没有右子节点的话,直接将max置为左子节点的索引
                max = 2 * k;
            }
            // 判断最大值是否小于当前节点,如果是则直接结束
            if (less(max,k)){
                break;
            }
            // 交换最大子节点和根节点的值
            exch(k,max);
            // 重置k的值
            k = max;
        }

    }

    /***
     * 判断索引i位置的值是否小于索引j位置的值
     * @param i
     * @param j
     * @return
     */
    public boolean less(int i, int j) {
        return this.heaps[i].compareTo(this.heaps[j]) < 0;
    }


    /***
     * 交换i索引和j索引位置的值
     * @param i
     * @param j
     */
    public void exch(int i, int j) {
        T temp = this.heaps[i];
        this.heaps[i] = this.heaps[j];
        this.heaps[j] = temp;
    }



    public static void main(String[] args) {
        Heap2<String> heap = new Heap2<String>(20);
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        heap.insert("F");
        heap.insert("G");
        String del;
        while ((del = heap.delMax()) != null) {
            System.out.print(del + ",");
        }
    }
}
