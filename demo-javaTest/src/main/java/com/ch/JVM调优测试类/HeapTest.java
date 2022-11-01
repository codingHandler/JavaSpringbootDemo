package com.ch.JVM调优测试类;

import java.util.ArrayList;

/**
 * @author: ch
 * @date: 2022/7/3 14:18
 * @description: JVM调优测试类
 */
public class HeapTest {
    byte[] a = new byte[1024 * 100]; // 100KB

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(10);
        }
    }
}
