package com.ch.黑马.algorithm.linear;


/**
 * @author: ch
 * @date: 2022/7/14 12:57
 * @description: 自定义实现线性表, 顺序表
 */
public class SequenceList<T> {
    /***
     * 存储元素的数组
     */
    private T[] dataArray;

    /***
     * 当前线性表元素个数
     */
    private int size;


    //构造方法
    public SequenceList(int capacity) {
        this.dataArray = (T[]) new Object[capacity];
        this.size = 0;
    }

    /***
     * 空置线性表
     */
    public void clear() {
        this.size = 0;
        this.dataArray = (T[]) new Object[dataArray.length];
    }

    /***
     * 判断线性表是否为空，是返回true，否返回false
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * 获取线性表中元素的个数
     * @return
     */
    public int length() {
        return size;
    }

    /***
     * 读取并返回线性表中的第i个元素的值
     * @param i
     * @return
     */
    public T get(int i) {
        return dataArray[i];
    }

    /***
     * 在线性表的第i个元素之前插入一个值为t的数据元素。
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (size == dataArray.length) {
            resize(dataArray.length * 2);
            //throw new RuntimeException("当前表已满");
        }
        if (i < 0 || i > size) {
            throw new RuntimeException("插入位置不合法");
        }
        for (int index = size; index > i; index--) {
            dataArray[index] = dataArray[index - 1];
        }
        dataArray[i] = t;
        size++;
    }

    /***
     * 向线性表中添加一个元素t
     * @param t
     */
    public void insert(T t) {
        if (size == dataArray.length) {
            resize(dataArray.length * 2);
            //throw new RuntimeException("当前表已满");
        } else {
            dataArray[size++] = t;
        }
    }

    /***
     * 删除并返回线性表中第i个数据元素
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i < 0 || i > size - 1) {
            throw new RuntimeException("当前要删除的元素不存在");
        }
        T res = dataArray[i];
        for (int index = i; index < size; index++) {
            dataArray[index] = dataArray[index + 1];
        }
        size--;
        if (size > 0 && this.dataArray.length > size / 4) {
            resize((this.dataArray.length) / 2);
        }
        return res;
    }

    /***
     * 返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
     * @param t
     * @return
     */
    public int indexOf(T t) {
        for (int index = 0; index < size; index++) {
            if (t.equals(dataArray[index])) {
                return index;
            }
        }
        return -1;
    }

    private void resize(int newSize) {
        T[] oldArray = this.dataArray;
        this.dataArray = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            this.dataArray[i] = oldArray[i];
        }
    }


    public static void main(String[] args) {
        SequenceList<String> sl = new SequenceList<>(2);
        //测试插入
        sl.insert("姚明");
        sl.insert("科比");
        sl.insert("麦迪");
        sl.insert(1, "詹姆斯");
        System.out.print("遍历结果:");
        for (int i = 0; i < sl.length(); i++) {
            if (i == sl.length() - 1) {
                System.out.println(sl.get(i));
            } else {
                System.out.print(sl.get(i) + ",");
            }
        }
        //测试获取
        String getResult = sl.get(1);
        System.out.println("获取索引1处的结果为：" + getResult);
        //测试删除
        String removeResult = sl.remove(0);
        System.out.println("删除的元素是：" + removeResult);
        for (int i = 0; i < sl.length(); i++) {
            if (i == sl.length() - 1) {
                System.out.println(sl.get(i));
            } else {
                System.out.print(sl.get(i) + ",");
            }
        }
        String str = "科比";
        int res = sl.indexOf(str);
        System.out.println(str + " :的索引位置为: " + res);
        //测试清空
        sl.clear();
        System.out.println("清空后的线性表中的元素个数为: " + sl.length());


    }


}
