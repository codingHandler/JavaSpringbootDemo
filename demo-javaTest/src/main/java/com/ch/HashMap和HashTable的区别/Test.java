package com.ch.HashMap和HashTable的区别;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @className: Test
 * @Auther: ch
 * @Date: 2022/4/21 22:57
 * @Description: TODO
 */
public class Test {
    public static void main(String[] args) {
        hashTableTest();
        //hashMapTest();
    }

    // 总结: hashMap允许空值空键
    private static void hashMapTest() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key1", null);
        map.put("key2", null);
        map.put(null, null);
    }

    // 总结: hashTable不允许空值空键
    private static void hashTableTest() {
        Hashtable<String, String> table = new Hashtable<>();
        table.put("key", "null");
        table.put(null, "value1");
    }
}
