package com.ch;

import java.security.PrivateKey;

/**
 * @author: ch
 * @date: 2022/9/28 17:09
 * @description: TODO
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
     /*   int test = test(30);
        System.out.println(test); // 打印832040*/
        threadTest();
    }

    public static int test(int i) {
        if (i <= 2) {
            return 1;
        }
        return test(i - 1) + test(i - 2);
    }

    private static Object mylock = new Object();
    private static volatile String flag = "A";

    public static void threadTest() throws InterruptedException {

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (mylock) {
                    if (flag.equals("A")) {
                        System.out.println("A");
                        flag = "B";
                        mylock.notify();
                    } else {
                        try {
                            mylock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread b = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (mylock) {
                    if (flag.equals("B")) {
                        System.out.println("B");
                        flag = "C";
                        mylock.notify();
                    } else {
                        try {
                            mylock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        Thread c = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (mylock) {
                    if (flag.equals("C")) {
                        System.out.println("C");
                        flag = "A";
                        mylock.notify();
                    } else {
                        try {
                            mylock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        a.start();

        b.start();

        c.start();


    }
}
