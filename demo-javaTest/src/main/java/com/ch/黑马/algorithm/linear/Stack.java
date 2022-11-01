package com.ch.黑马.algorithm.linear;

import org.w3c.dom.Node;

import java.util.Iterator;

/**
 * @author: ch
 * @date: 2022/7/24 14:12
 * @description: 自定义实现栈
 */
public class Stack<T> implements Iterable<T> {

    /**
     * 记录首结点
     **/
    private Node head;
    /**
     * 记录节点个数
     **/
    private Integer size;


    public Stack() {
        this.head = new Node(null, null);
        this.size = 0;
    }

    /***
     * 判断栈是否为空，是返回true，否返回false
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * 获取栈中元素的个数
     * @return
     */
    public int size() {
        return size;
    }

    /***
     * 弹出栈顶元素
     * @return
     */
    public T pop() {
        // 判断栈是否为空,如果为空直接返回null
        if (this.head.next == null) {
            return null;
        }
        // 如果栈不为空,则返回第一个节点,同时将头节点的下一个节点指向当前节点的下一个节点
        Node node = this.head.next;
        this.head = node.next;
        node.next = null;
        this.size--;
        return node.item;
    }

    /***
     * 向栈中压入元素t
     * @param t
     */
    public void push(T t) {
        if (this.head.next == null) {
            this.head.next = new Node(t, null);
        } else {
            Node node = this.head.next;
            Node newNode = new Node(t, node);
            this.head.next = newNode;
        }
        this.size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Siterator();
    }

    private class Siterator implements Iterator<T> {
        private Node node = head.next;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            Node oldNode = node;
            node = oldNode.next;
            return oldNode.item;
        }
    }

    /**
     * @author ch
     * @date 2022/7/24 14:15
     * @description Node节点类
     **/
    public class Node {
        private T item;
        private Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        for (String str : stack) {
            System.out.println(str);
        }
        System.out.println("-----------------------------");
        String result = stack.pop();
        System.out.println("弹出了元素：" + result);
        System.out.println(stack.size());
    }
}
