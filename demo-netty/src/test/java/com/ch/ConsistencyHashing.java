package com.ch;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2022/4/15 10:51
 * @Description: TODO
 */
public class ConsistencyHashing {
    //虚拟节点个数
    private static final int VIRTUAL_NODE_NUM = 5;

    //虚拟节点分配,key是hash值,value是虚拟节点服务器名称
    private static SortedMap<Integer, String> virtualNodeMap = new TreeMap<>();

    //真实节点列表
    private static List<String> realNodes = new LinkedList<>();

    //模拟初始服务器
    private static String[] servers = {"192.168.1.1", "192.168.1.2", "192.168.1.3"};

    static {
        for (String server : servers) {
            addNode(server);
        }
    }

    /**
     * 获取被分配的节点名
     *
     * @param node
     * @return
     */
    public static String getServer(String node) {
        int hash = getHash(node);
        Integer key;
        SortedMap<Integer, String> subMap = virtualNodeMap.tailMap(hash);
        if (subMap.isEmpty()) {
            key = virtualNodeMap.lastKey();
        } else {
            key = subMap.firstKey();
        }
        String virtualNodeName = virtualNodeMap.get(key);
        return virtualNodeName.substring(0, virtualNodeName.indexOf("&&"));

    }

    /**
     * 添加节点
     *
     * @param node
     */
    public static void addNode(String node) {
        if (!realNodes.contains(node)) {
            realNodes.add(node);
            System.out.println("真实节点[" + node + "]上线添加");
            for (int i = 0; i < VIRTUAL_NODE_NUM; ++i) {
                String virtualNodeName = node + "&&virtualNode" + i;
                int hash = getHash(virtualNodeName);
                virtualNodeMap.put(hash, virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "],hash:" + hash + "被添加");
            }
        }
    }

    /**
     * 删除节点
     *
     * @param node
     */
    public static void delNode(String node) {
        if (realNodes.contains(node)) {
            realNodes.remove(node);
            System.out.println("真实节点[" + node + "]下线移除");
            for (int i = 0; i < VIRTUAL_NODE_NUM; ++i) {
                String virtualNodeName = node + "&&virtualNode" + i;
                int hash = getHash(virtualNodeName);
                virtualNodeMap.remove(hash);
                System.out.println("虚拟节点[" + virtualNodeName + "],hash:" + hash + "被添加");
            }
        }
    }

    /**
     * FNV1_32_HASH算法
     *
     * @param str
     * @return
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        //如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    public static void main(String[] args) {
        //模拟客户端的请求
        String[] clientNodes = {"127.0.0.1:8070", "127.0.0.1:8080", "127.0.0.1:8090"};
        for (String node : clientNodes) {
            System.out.println("[" + node + "]的hash值为" + getHash(node) + ",被路由到结点[" + getServer(node) + "]");
        }
        //添加一个节点
        addNode("192.168.1.4");
        //删除一个节点
        delNode("192.168.1.2");
        for (String node : clientNodes) {
            System.out.println("[" + node + "]的hash值为" + getHash(node) + ",被路由到结点[" + getServer(node) + "]");
        }
    }
}

