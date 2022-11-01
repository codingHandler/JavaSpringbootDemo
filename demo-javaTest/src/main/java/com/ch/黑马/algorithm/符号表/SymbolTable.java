package com.ch.黑马.algorithm.符号表;

import lombok.val;
import org.w3c.dom.Node;

/**
 * @author: ch
 * @date: 2022/7/24 17:19
 * @description: 自定义实现符号表
 */
public class SymbolTable<K, V> {

    private Node head;
    private Integer size;

    public SymbolTable() {
        this.head = new Node(null, null, null);
        this.size = 0;
    }


    /***
     * 根据键key，找对应的值
     * @param key
     * @return
     */
    public V get(K key) {
        Node node = this.head.next;

        while (node != null) {
            // 判断是否有key相同的节点
            if (node.key.equals(key)) {
                return node.value;
            } else {
                node = node.next;
            }
        }
        return null;
    }

    /***
     * 向符号表中插入一个键值对
     * @param key
     * @param val
     */
    public void put(K key, V val) {
        Node node = this.head.next;
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = val;
                return;
            } else {
                node = node.next;
            }
        }
        Node oldNode = this.head.next;
        Node newNode = new Node(key, val, oldNode);
        this.head.next = newNode;
        this.size++;
    }

    /***
     * 删除键为key的键值对
     * @param key
     */
    public void delete(K key) {
        Node pre = this.head;
        Node node = this.head.next;
        while (node != null) {
            if (key.equals(node.key)) {
                node = node.next;
                pre.next = node;
                this.size--;
            } else {
                pre = node;
                node = node.next;
            }
        }
    }

    /***
     * 获取符号表的大小
     * @return
     */
    public int size() {
        return size;
    }


    class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SymbolTable<Integer, String> st = new SymbolTable<>();
        st.put(1, "张三");
        st.put(3, "李四");
        st.put(5, "王五");
        System.out.println(st.size());
        st.put(1, "老巴");
        System.out.println(st.get(1));
        System.out.println(st.size());
        st.delete(1);
        System.out.println(st.size());
        System.out.println(st.get(1));
        System.out.println(st.get(3));
        System.out.println(st.get(5));
    }
}
