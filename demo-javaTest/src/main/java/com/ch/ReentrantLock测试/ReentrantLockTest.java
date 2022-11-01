package com.ch.ReentrantLock测试;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ch
 * @date: 2022/8/22 10:44
 * @description: TODO
 */
public class ReentrantLockTest {
    /**
     * 定义count = 0;
     */
    private static int count = 0;

    public static void main(String[] args) {
       // new ReentrantLockTest().synchronizedTest();
        new ReentrantLockTest().reentrantLockTest();
    }

    private void reentrantLockTest() {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    try {
                        lock.lock();
                        count++;
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("值:"+count);
    }

    private void synchronizedTest() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    synchronized (this) {
                        count++;
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("值:"+count);
    }


}
