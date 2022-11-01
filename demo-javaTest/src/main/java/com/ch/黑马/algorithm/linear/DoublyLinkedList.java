package com.ch.黑马.algorithm.linear;

/**
 * @author: ch
 * @date: 2022/7/21 13:12
 * @description: 自定义双向链表实现
 */
public class DoublyLinkedList<T> {

    // 头结点
    private Node head;
    // 尾结点
    private Node tail;
    // 节点个数
    private int size;

    public DoublyLinkedList() {
        Node node = new Node(null, null, null);
        this.head = node;
        this.tail = null;
        this.size = 0;
    }

    /***
     * 空置线性表
     */
    public void clear() {
        this.head.next = null;
        this.tail = null;
        this.size = 0;
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
        if (i < 0 || i >= size) {
            throw new RuntimeException("无此节点");
        }
        Node node = null;
        if (i > size / 2) {
            node = tail;
            for (int j = 0; j < size - i - 1; j++) {
                node = node.previous;
            }
        } else {
            node = head.next;
            for (int j = 0; j < i; j++) {
                node = node.next;
            }
        }
        return node.item;
    }

    /***
     * 往线性表中添加一个元素；
     * @param t
     */
    public void insert(T t) {
        Node node = null;
        // 判断链表是否为空
        if (isEmpty()) {
            // 创建新的节点,他的前驱指向头节点,后继为空
            node = new Node(t, this.head, null);
            // 新的节点成为头结点的后继节点
            this.head.next = node;
            this.tail = node;
        } else {
            // 创建新的节点,他的前驱节点为该链表的尾结点,后继为空
            node = new Node(t, this.tail, null);
            // 让新的节点成为尾结点的后继节点
            this.tail.next = node;
            // 让该节点成为新的尾结点
            this.tail = node;
        }
        this.size++;
    }

    /***
     * 在线性表的第i个元素之前插入一个值为t的数据元素。
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        // 判断索引位置是否合法
        if (i > size) {
            throw new RuntimeException("非法索引");
        }
        Node node = null;
        Node newNode = null;
        if (i == size - 1) {
            node = this.tail;
            newNode = new Node(t, this.tail.previous, null);
            node.previous.next = newNode;
            this.tail = newNode;
        } else {
            // 判断该索引位置离头节点近还是离尾节点近
            if (i > size / 2) {
                node = this.tail.previous;
                for (int j = 0; j < size - i; j++) {
                    node = node.previous;
                }
                // 创建新节点,前驱节点为原来该索引位置的上一个节点,后继节点为原来此索引位置的节点
                newNode = new Node(t, node.previous, node.next);

            } else {
                node = this.head.next;
                for (int j = 0; j < i; j++) {
                    node = node.next;
                }
                // 创建新的节点,前驱节点为原来该位置的节点的上一个节点,后继节点为原来该位置的节点
                newNode = new Node(t, node.previous, node.next);

            }
            // 将新节点成为原来节点上一个节点的后继节点
            node.previous.next = newNode;
            // 将当前节点的下一个前驱节点指向新节点
            node.next.previous = newNode;
            node.previous = null;
            node.next = null;
        }

    }

    /***
     * 删除并返回线性表中第i个数据元素。
     * @param i
     * @return
     */
    public T remove(int i) {
        if (i >= size) {
            throw new RuntimeException("非法索引");
        }
        Node node = null;
        // 判断删除的是否是尾结点
        if (i == size - 1) {
            node = this.tail;
            this.tail = this.tail.previous;
            this.tail.next = null;
        } else {
            if (i > size / 2) {
                node = this.tail;
                for (int j = 0; j < size - i - 1; j++) {
                    node = node.previous;
                }
            } else {
                node = this.head.next;
                for (int j = 0; j < i; j++) {
                    node = node.next;
                }
            }
            // 将原来该位置的节点的上一个节点的下一个节点指向原来节点的下一个节点
            node.previous.next = node.next;
            // 将原来节点的前驱节点重置为null
            node.previous = null;
            // 将原来节点的后继节点重置为null
            node.next = null;
        }
        size--;
        return node.item;
    }

    /***
     * 返回线性表中首次出现的指定的数据元素的位序号，若不存在，则返回-1。
     * @param t
     * @return
     */
    public int indexOf(T t) {
        Node node = this.head.next;
        for (int i = 0; i < size; i++) {
            if (node.item.equals(t)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    /***
     * 获取第一个元素
     * @return
     */
    public T getFirst() {
        return this.head.next.item;
    }

    /***
     * 获取最后一个元素
     */
    public T getLast() {
        return this.tail.item;
    }


    /**
     * @author ch
     * @date 2022/7/21 13:19
     * @description 节点类
     **/
    private class Node {
        // 存储数据
        private T item;
        // 指向当前节点的上一个节点
        private Node previous;
        // 指向当前节点的下一个节点
        private Node next;

        public Node(T item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.insert("乔峰");
        list.insert("虚竹");
        list.insert("段誉");
        list.insert(1, "鸠摩智");
        list.insert(2, "叶二娘");
        for (int i = 0; i < list.length(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------------------");
        String tow = list.get(2);
        System.out.println("获取索引为 2 的元素值是:" + tow);
        System.out.println("-------------------------");
        String remove = list.remove(2);
        System.out.println("删除索引为 2 位置的元素值为: " + remove);
        System.out.println("删除后链表长度为: " + list.length());
        System.out.println("--------------------");
        System.out.println("头结点为: " + list.getFirst());
        System.out.println("尾节点为: " + list.getLast());
        System.out.println("--------------------");
        int index = list.indexOf("乔峰");
        System.out.println("乔峰的索引位置为: " + index);
    }
}
