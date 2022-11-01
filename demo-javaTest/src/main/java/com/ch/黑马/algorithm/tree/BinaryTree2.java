package com.ch.黑马.algorithm.tree;

import com.ch.黑马.algorithm.linear.Queue;

/**
 * @author: ch
 * @date: 2022/7/24 19:54
 * @description: 自定义实现二叉查找树
 */
public class BinaryTree2<K extends Comparable<K>, V> {
    private Node root = null;
    private Integer size = 0;


    /***
     * 向树中插入一个键值对
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    /***
     * 给指定树x上，添加键一个键值对，并返回添加后的新树
     * @param x
     * @param key
     * @param value
     * @return
     */
    private Node put(Node x, K key, V value) {
        // 1,判断当前节点是否为空,如果没有则创建新增节点为当前节点
        if (x == null) {
            x = new Node(null, null, key, value);
            this.size++;
        } else {
            int res = key.compareTo(x.key);
            if (res > 0) {
                // 2,如果新增节点key大于当前节点key,则继续找当前节点的右子树
                x.right = put(x.right, key, value);
            } else if (res < 0) {
                // 3,如果新增节点key小于当前节点key,则继续找当前节点的左子树
                x.left = put(x.left, key, value);
            } else {
                // 4,如果新增节点key等于当前接点,则替换当前节点的value
                x.value = value;
            }
        }
        return x;
    }

    /***
     * 根据key，从树中找出对应的值
     * @param key
     * @return
     */
    public V get(K key) {
        return get(root, key);
    }

    /***
     * 从指定的树x中，找出key对应的值
     * @param x
     * @param key
     * @return
     */
    private V get(Node x, K key) {
        // 1,如果当前节点为null,则直接返回null
        if (x == null) {
            return null;
        }
        // 2,如果当前节点不为空,比较查询节点key和当前节点key的大小
        int res = key.compareTo(x.key);
        if (res > 0) {
            // 2.1如果查询节点key大于当前节点key,则继续找当前节点的右子树
            return get(x.right, key);
        } else if (res < 0) {
            // 2.2如果查询节点key小于当前节点key,则继续找当前节点的左子树
            return get(x.left, key);
        } else {
            // 2.3如果查询节点key等于当前节点key,则找到了,返回当前节点的value
            return x.value;
        }
    }

    /***
     * 根据key，删除树中对应的键值对
     * @param key
     */
    public void delete(K key) {
        delete(root, key);
    }

    /***
     * K删除指定树x上的键为key的键值对，并返回删除后的 新树
     * @param x
     * @param key
     * @return
     */
    private Node delete(Node x, K key) {
        // 1,如果当前节点为null,则没有找到对应的key节点
        if (x == null) {
            return null;
        } else {
            // 2,如果当前节点不为null,则比较key和当前节点key的大小
            int res = key.compareTo(x.key);
            if (res > 0) {
                // 3,如果删除节点key大于当前节点key,则继续找当前节点的右子树
                x.right = delete(x.right, key);
            } else if (res < 0) {
                // 4,如果删除节点key小于当前节点key,则继续找当前节点的左子树
                x.left = delete(x.left, key);
            } else {
                // 5,如果删除节点key等于当前节点key,则找到了需要删除的节点,size--
                size--;
                // 5.1,如果需删除节点左子树为null,也就是说没有左子树,那么直接返回右子树
                if (x.left == null) {
                    return x.right;
                }
                // 5.2,如果需删除节点右子树为null,也就是说没有右子树,那么直接返回左子树
                if (x.right == null) {
                    return x.left;
                }
                // 5.3,如果需删除节点既有左子树,又有右子树,则从右子树中获取并删除最小节点
                // 最小节点
                Node minNode = x.right;
                // 最小节点的上一个节点
                Node preNode = x;
                while (minNode.left != null) {
                    preNode = minNode;
                    minNode = minNode.left;
                }
                // 5.4,将最小节点替换当前节点,将当前节点的左子树指向最小节点,当前节点的右子树指向最小节点
                preNode.left = null;
                minNode.left = x.left;
                minNode.right = x.right;
                x = minNode;
            }
            return x;
        }


    }

    /***
     * 获取树中元素的个数
     * @return
     */
    public int size() {
        return size;
    }


    /***
     * 前序遍历整棵树
     * @return
     */
    public Queue<K> preErgodic() {
        Queue<K> queue = new Queue<>();
        preErgodic(root, queue);
        return queue;
    }

    /***
     * 前序遍历指定x树
     * @param x
     * @param queue
     * @return
     */
    private void preErgodic(Node x, Queue queue) {
        if (x == null) {
            return;
        } else {
            // 将当前节点添加进queue队列中
            queue.enqueue(x.key);
            // 判断当前节点是否有左子树,如果有则递归遍历左子树
            if (x.left != null) {
                preErgodic(x.left, queue);
            }
            // 判断当前节点是否有右子树,如果有则递归遍历右子树
            if (x.right != null) {
                preErgodic(x.right, queue);
            }
        }
    }


    /***
     * 中序遍历整棵树
     * @return
     */
    public Queue<K> midErgodic() {
        Queue<K> queue = new Queue<>();
        midErgodic(root, queue);
        return queue;
    }

    /***
     * 中序遍历指定x树
     * @param x
     * @param queue
     */
    private void midErgodic(Node x, Queue queue) {
        if (x == null) {
            return;
        } else {
            // 判断当前节点是否有左子树,有则递归遍历左子树
            if (x.left != null) {
                midErgodic(x.left, queue);
            }
            // 将当前节点放入队列中
            queue.enqueue(x.key);
            // 判断当前节点是否有右子树,有则递归遍历右子树
            if (x.right != null) {
                midErgodic(x.right, queue);
            }
        }
    }


    /***
     * 后序遍历整课树
     * @return
     */
    public Queue<K> afterErgodic() {
        Queue<K> queue = new Queue<>();
        afterErgodic(root, queue);
        return queue;
    }

    /***
     * 后序遍历指定x树
     * @param x
     * @param queue
     */
    private void afterErgodic(Node x, Queue queue) {
        if (x == null) {
            return;
        } else {
            // 判断当前节点是否有左子树,有则递归遍历当前节点左子树
            if (x.left != null) {
                afterErgodic(x.left, queue);
            }
            // 判断当前节点是否有右子树,有则递归遍历当前节点右子树
            if (x.right != null) {
                afterErgodic(x.right, queue);
            }
            // 将当前节点添加到对列
            queue.enqueue(x.key);
        }
    }


    /***
     * 层序遍历---广度优先遍历
     * @return
     */
    public Queue<K> layerErgodic() {
        // 1,创建一个存储节点的的nodes对列
        Queue<Node> nodes = new Queue<>();
        // 2,创建一个存储K的keys对列
        Queue<K> keys = new Queue<>();
        // 3,将根节点放入nodes对列
        if (root != null) {
            nodes.enqueue(root);
        }
        // 4,循环弹出nodes队列中的节点
        while (!nodes.isEmpty()) {
            // 5,将弹出节点的K放入keys对列中
            Node node = nodes.dequeue();
            keys.enqueue(node.key);
            // 6,判断弹出节点是否有左子树,如果有则加入nodes对列中
            if (node.left != null) {
                nodes.enqueue(node.left);
            }
            // 7,判断弹出节点是否有右子树,如果有则加入nodes队列中
            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }
        return keys;
    }


    /***
     * 获取整棵树的最大深度
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /***
     * 获取指定节点x树的最大深度
     * @param x
     * @return
     */
    private int maxDepth(Node x) {
        // 判断当前节点是否是null,是则返回0
        if (x == null) {
            return 0;
        } else {
            // 最大深度
            int max = 0;
            // 左子树最大深度
            int lMax = 0;
            // 右子树最大深度
            int rMax = 0;
            // 判断左子树是否还有节点,如果有则递归获取左子树的最大深度
            if (x.left != null) {
                lMax = maxDepth(x.left);
            }
            // 判断右子树是否还有节点,如果有则递归遍历右子树的最大深度
            if (x.right != null) {
                rMax = maxDepth(x.right);
            }
            max = rMax > lMax ? rMax + 1 : lMax + 1;
            return max;
        }

    }

    class Node {
        private Node left;
        private Node right;
        private K key;
        private V value;

        public Node(Node left, Node right, K key, V value) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.value = value;
        }
    }


    public static void main(String[] args) {
        //testTree();
        testErgodic();
    }

    private static void testErgodic() {
        BinaryTree2<String, String> bt = new BinaryTree2<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");
        //Queue<String> queue = bt.preErgodic(); // EBADCGFH
        //Queue<String> queue = bt.midErgodic();  // ABCDEFGH
        //Queue<String> queue = bt.afterErgodic(); // ACDBFHGE
        Queue<String> queue = bt.layerErgodic();   // EBGADFHC
        for (String key : queue) {
            System.out.println(key + "=" + bt.get(key));
        }
        System.out.println("最大深度: " + bt.maxDepth());
    }

    static void testTree() {
        BinaryTree2<Integer, String> bt = new BinaryTree2<>();
        bt.put(4, "二哈");
        bt.put(1, "张三");
        bt.put(3, "李四");
        bt.put(5, "王五");
        System.out.println(bt.size());
        bt.put(1, "老三");
        System.out.println(bt.get(1));
        System.out.println(bt.size());
        bt.delete(1);
        System.out.println(bt.size());
    }
}
