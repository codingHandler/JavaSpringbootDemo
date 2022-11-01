package com.ch.黑马.algorithm.linear;

import java.util.Iterator;

/**
 * @author: ch
 * @date: 2022/7/24 16:41
 * @description: 自定义实现对列
 */
public class Queue<T> implements Iterable<T> {

    private Node firstNode;
    private Node lastNode;
    private Integer size;

    public Queue() {
        this.firstNode = new Node(null, null);
        this.lastNode = null;
        this.size = 0;
    }

    /***
     * 判断队列是否为空，是返回true，否返回false
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * 获取队列中元素的个数
     * @return
     */
    public int size() {
        return size;
    }

    /***
     * 从队列中拿出一个元素
     * @return
     */
    public T dequeue() {
        if (lastNode == null) {
            return null;
        }
        Node node = this.firstNode.next;
        this.firstNode.next = node.next;
        node.next = null;
        this.size--;
        return node.item;
    }

    /***
     * 往队列中插入一个元素
     * @param t
     */
    public void enqueue(T t) {
        if (this.firstNode.next == null) {
            this.lastNode = new Node(t, null);
            this.firstNode.next = this.lastNode;
        } else {
            Node oldNode = this.lastNode;
            Node node = new Node(t, null);
            oldNode.next = node;
            this.lastNode = node;
        }
        this.size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    class SIterator implements Iterator<T> {

        private Node node = firstNode.next;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            Node oldNode = node;
            node = node.next;
            return oldNode.item;
        }
    }


    public class Node {
        T item;
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {
        Queue<String> queue = new Queue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        for (String str : queue) {
            System.out.print(str + " ");
        }
        System.out.println("-----------------------------");
        String result = queue.dequeue();
        System.out.println("出列了元素：" + result);
        System.out.println("剩余元素数量:"+queue.size());
        for (String str : queue) {
            System.out.print(str + " ");
        }
    }

}
