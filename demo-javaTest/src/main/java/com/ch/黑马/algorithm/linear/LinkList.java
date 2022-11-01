package com.ch.黑马.algorithm.linear;

import org.w3c.dom.Node;

/**
 * @author: ch
 * @date: 2022/7/18 22:31
 * @description: TODO
 */
public class LinkList<T> {

    // 记录头结点
    private Node head;
    // 记录链表长度
    private int size;

    // 节点对象
    private class Node {
        //存储数据
        T item;
        //下一个结点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkList() {
        this.head = new Node(null, null);
        this.size = 0;
    }

    /***
     * 空置线性表
     */
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /***
     * 判断线性表是否为空，是返回true，否返回false
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /***
     * 获取线性表中元素的个数
     * @return
     */
    public int length() {
        return this.size;
    }

    /***
     * 读取并返回线性表中的第i个元素的值
     * @param i
     * @return
     */
    public T get(int i) {
        if (i >= size) {
            throw new RuntimeException("获取失败,无此节点");
        }
        Node node = this.head;
        for (int j = 0; j < i + 1; j++) {
            node = node.next;
        }
        return node.item;
    }

    /***
     * 往线性表中添加一个元素；
     * @param t
     */
    public void insert(T t) {
        Node node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        if (node.next == null) {
            node.next = new Node(t, null);
            this.size++;
        }
    }

    /***
     * 在线性表的第i个元素之前插入一个值为t的数据元素。
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (i > size) {
            throw new RuntimeException("索引异常,插入失败");
        }
        Node node = this.head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        Node next = node.next;
        node.next = new Node(t, next);
        size++;
    }


    /***
     * 删除并返回线性表中第i个数据元素。
     * @param i
     * @return
     */
    public T remove(int i) {
        Node node = this.head;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        Node removeNode = node.next;
        node.next = removeNode.next;
        this.size--;
        return removeNode.item;
    }

    /***
     * 返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
     * @param t
     * @return
     */
    public int indexOf(T t) {
        Node node = this.head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /***
     * 反转链表
     */
    public void reverse() {
        reverse(this.head.next);
    }

    /***
     * 反转方法
     * @param node
     * @return
     */
    public Node reverse(Node node) {
        // 函数的出口
        if (node.next == null) {
            this.head.next = node;
            return node;
        }
        // 递归反转当前节点node的下一个节点,返回值就是链表反转前,当前链表最后一个元素
        Node preNode = reverse(node.next);
        // 让当前节点成为返回的节点的下一个节点
        preNode.next = node;
        // 重置当前节点的下一个节点为空
        //node.next = null;
        return node;

    }


    public static void main(String[] args) {
        LinkList<String> sl = new LinkList<>();
        //测试插入
        sl.insert("姚明");
        sl.insert("科比");
        sl.insert("麦迪");

        System.out.print("遍历结果:");
        for (int i = 0; i < sl.length(); i++) {
            if (i == sl.length() - 1) {
                System.out.println(sl.get(i));
            } else {
                System.out.print(sl.get(i) + ",");
            }
        }

        sl.insert(1, "詹姆斯");
        System.out.print("遍历结果:");
        for (int i = 0; i < sl.length(); i++) {
            if (i == sl.length() - 1) {
                System.out.println(sl.get(i));
            } else {
                System.out.print(sl.get(i) + ",");
            }
        }
        // 反转链表测试
        sl.reverse();
        System.out.print("反转链表遍历结果:");
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
        System.out.print("遍历结果:");
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
