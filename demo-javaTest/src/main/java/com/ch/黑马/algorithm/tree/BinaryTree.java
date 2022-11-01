package com.ch.黑马.algorithm.tree;

import com.ch.黑马.algorithm.linear.Queue;

/**
 * @author: ch
 * @date: 2022/7/24 19:54
 * @description: 自定义实现二叉查找树
 */
public class BinaryTree<K extends Comparable<K>, V> {
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
        // 判断x节点是否为空
        if (x == null) {
            this.size++;
            return new Node(null, null, key, value);
        } else {
            int res = key.compareTo(x.key);
            if (res > 0) {
                // 新节点的key大于当前节点key,继续找当前节点的右子节点
                x.right = put(x.right, key, value);
            } else if (res < 0) {
                // 新节点的key小于当前节点的key,继续找当前节点的左子节点
                x.left = put(x.left, key, value);
            } else {
                // 新节点的key等于当前节点的key,修改当前节点的value
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
        // 当前节点是否为空
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res > 0) {
            // 如果查询的key大于当前节点key,继续找右子树
            return get(x.right, key);
        } else if (res < 0) {
            // 如果查询的key小于当前节点key,继续找左子树
            return get(x.left, key);
        } else {
            // 如果查询的key等于当前节点的key,则返回当前节点的value值
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
        // 1,如果当前节点为null
        if (x == null) {
            return null;
        }
        int res = key.compareTo(x.key);
        if (res > 0) {
            // 2,如果要删除的节点key大于当前节点的key,则继续找右子树
            x.right = delete(x.right, key);
        } else if (res < 0) {
            // 3,如果要删除的节点key小于当前节点的key,则继续找左子树
            x.left = delete(x.left, key);
        } else {
            size--;
            // 4,如果要删除的节点key等于点前节点的key,则当前节点就是需要删除的节点
            // 4.1如果当前节点的左子树为null,则直接返回当前节点的右子树
            if (x.left == null) {
                return x.right;
            }
            // 4.2如果当前节点的右子树为null,则直接返回当前节点的左子树
            if (x.right == null) {
                return x.left;
            }
            // 4.3如果当前节点的左子树和右子树都不为null,则获取右子树的最小值
            // 4.3.1记录右子树的最小值
            Node minNode = x.right;
            // 4.3.2记录当前节点的上一个节点
            Node preNode = x;
            while (minNode.left != null) {
                preNode = minNode;
                minNode = minNode.left;
            }
            // 4.3.3删除右子树中最小节点
            preNode.left = null;
            minNode.left = x.left;
            minNode.right = x.right;
            x = minNode;
        }
        return x;
    }


    /***
     * 找出树中最小的键
     * @return
     */
    public K min() {
        Node min = min(root);
        return min.key;
    }

    /***
     * 找出指定树x中，最小键所在的结点
     * @param x
     * @return
     */
    private Node min(Node x) {
        // 判断当前节点是否为空,为空则返回null
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }

    /***
     * 找出树中最大键
     * @return
     */
    public K max() {
        Node max = max(root);
        return max.key;
    }

    /***
     * 找出指定x树中,最大节点
     * @param x
     * @return
     */
    public Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        } else {
            return x;
        }
    }


    /***
     * 使用前序遍历，获取整个树中的所有键
     * @return
     */
    public Queue<K> preErgodic() {
        Queue<K> queue = new Queue<>();
        preErgodic(root, queue);
        return queue;
    }

    /***
     * 使用前序遍历，把指定树x中的所有键放入到keys队列中
     * @param x
     * @param keys
     */
    private void preErgodic(Node x, Queue<K> keys) {
        // 判断当前节点是否为null,为null直接返回
        if (x == null) {
            return;
        } else {
            // 将当前节点key放入对列keys中
            keys.enqueue(x.key);
            // 如果当前节点的左子树不为空,则递归遍历左子树
            if (x.left != null) {
                preErgodic(x.left, keys);
            }
            // 如果当前节点的右子树不为空,则递归遍历右子树
            if (x.right != null) {
                preErgodic(x.right, keys);
            }
        }
    }

    /***
     * 使用后序遍历，获取整个树中的所有键
     * @return
     */
    public Queue<K> afterErgodic() {
        Queue<K> queue = new Queue<>();
        afterErgodic(root, queue);
        return queue;
    }

    /***
     * 使用后序遍历，把指定树x中的所有键放入到keys队列中
     * @param x
     * @param keys
     */
    private void afterErgodic(Node x, Queue<K> keys) {
        // 判断当前节点是否为null
        if (x == null) {
            return;
        } else {
            // 判断当前节点左子树是否为null,不为空则递归遍历左子树
            if (x.left != null) {
                afterErgodic(x.left, keys);
            }
            // 判断当前节点右子树是否为null,不为空则递归遍历右子树
            if (x.right != null) {
                afterErgodic(x.right, keys);
            }
            // 将当前节点放入对列
            keys.enqueue(x.key);
        }

    }


    /***
     * 使用中序遍历,获取整个树的键
     * @return
     */
    public Queue<K> midErgodic() {
        Queue<K> queue = new Queue<>();
        midErgodic(root, queue);
        return queue;
    }

    /***
     * 使用中序遍历,把指定树x中的所有键放入到keys队列中
     * @param x
     * @param keys
     */
    public void midErgodic(Node x, Queue<K> keys) {
        // 判断当前节点是否为null,是null则直接返回
        if (x == null) {
            return;
        } else {
            // 判断左子树是否为null,不为空则递归遍历左子树
            if (x.left != null) {
                midErgodic(x.left, keys);
            }
            // 把当前节点放入队列keys中
            keys.enqueue(x.key);

            // 判断右子树是否为null,不为空则递归遍历右子树
            if (x.right != null) {
                midErgodic(x.right, keys);
            }
        }
    }


    /***
     * 层序遍历--也叫广度优先遍历
     * @return
     */
    public Queue<K> layerErgodic() {
        // 存放弹出节点的键K
        Queue<K> keys = new Queue<>();
        // 初始队列,存放Node节点
        Queue<Node> nodes = new Queue<>();
        // 将根节点放入nodes队列中
        nodes.enqueue(root);
        // 循环弹出nodes队列中的节点,放到keys对列中
        while (!nodes.isEmpty()) {
            // 获取弹出节点
            Node node = nodes.dequeue();
            // 将弹出节点key放入keys队列中
            keys.enqueue(node.key);
            // 判断弹出节点是否有左子节点
            if (node.left != null) {
                // 将左子节点放入node对列中
                nodes.enqueue(node.left);
            }
            // 判断弹出节点是或否有右子节点
            if (node.right != null) {
                // 将右子节点放入keys中
                nodes.enqueue(node.right);
            }
        }
        return keys;
    }


    /***
     * 计算整个树的最大深度
     * @return
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /***
     * 计算指定树x的最大深度
     * @param x
     * @return
     */
    private int maxDepth(Node x) {
        if (x == null) {
            return 0;
        } else {
            // 最大深度
            int max = 0;
            // 左子树最大深度
            int lmax = 0;
            // 右子树最大深度
            int rmax = 0;

            if (x.left != null) {
                // 左子树不为null
                lmax = maxDepth(x.left);
            }
            if (x.right != null) {
                rmax = maxDepth(x.right);
            }
            // 当前树的最大深度=左子树的最大深度和右子树的最大深度中的较大者+1
            max = lmax > rmax ? lmax + 1 : rmax + 1;
            return max;
        }
    }

    /***
     * 获取树中元素的个数
     * @return
     */
    public int size() {
        return size;
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
        // 测试树
        //treeTest();
        // 测试遍历
        //testErgodic();
        //测试最大深度
        testMaxDepth();
    }

    private static void testMaxDepth() {
        BinaryTree<String, String> bt = new BinaryTree<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");
        System.out.println("最大深度: "+bt.maxDepth());
    }

    /***
     * 测试前序遍历
     */
    private static void testErgodic() {
        BinaryTree<String, String> bt = new BinaryTree<>();
        bt.put("E", "5");
        bt.put("B", "2");
        bt.put("G", "7");
        bt.put("A", "1");
        bt.put("D", "4");
        bt.put("F", "6");
        bt.put("H", "8");
        bt.put("C", "3");
        //Queue<String> queue = bt.preErgodic();
        //Queue<String> queue = bt.midErgodic();
        //Queue<String> queue = bt.afterErgodic();
        Queue<String> queue = bt.layerErgodic();
        for (String key : queue) {
            System.out.println(key + "=" + bt.get(key));
        }
    }

    /***
     * 树测试
     */
    public static void treeTest() {
        BinaryTree<Integer, String> bt = new BinaryTree<>();
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
        System.out.println("最小键: " + bt.min());
        System.out.println("最大键: " + bt.max());
    }
}
