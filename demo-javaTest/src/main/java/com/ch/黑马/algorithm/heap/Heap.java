package com.ch.黑马.algorithm.heap;

/**
 * @author: ch
 * @date: 2022/7/26 21:32
 * @description: 自定义实现堆
 */
public class Heap<T extends Comparable> {

    private T[] heap;

    private int size;

    public Heap(int capacity) {
        this.heap = (T[]) new Comparable[capacity + 1];
        this.size = 0;
    }

    /***
     * 判断堆中索引i处的元素是否小于索引j处的元素
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    /***
     * 交换堆中i索引和j索引处的值
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        T temp = heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }

    /***
     * 删除堆中最大的元素,并返回这个最大元素
     * @return
     */
    public T delMax() {
        // 判断堆中的元素个数是否大于1,如果大于1则直接返回并删除当前节点
        if (size == 1) {
            T node = heap[1];
            heap[1] = null;
            return node;
        } else {
            T node = heap[1];
            // 交换当前节点和最左节点
            exch(1, size);
            // 将最左节点删除,同时使用size--进行size减操作
            heap[size--] = null;
            // 将索引为1处的节点进行下沉操作
            sink(1);
            return node;
        }
    }

    /***
     * 往堆中插入一个元素
     * @param t
     */
    public void insert(T t) {
        heap[++size] = t;
        swim(size);
    }

    /***
     * 使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     * @param k
     */
    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    /***
     * 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
     * @param k
     */
    private void sink(int k) {
        // 如果节点2*k小于或等于当前堆的节点数,则进入下沉操作
        while (2 * k <= size) {
            int max;
            // 比较节点的左右子树的大小,取值更大的交换
            if (2 * k + 1 <= size) {
                if (less(2 * k,2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }
            if (less(max, k)) {
                break;
            }
            exch(max, k);
            k = max;
        }
    }

    public static void main(String[] args) {
        Heap<String> heap = new Heap<String>(20);
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
