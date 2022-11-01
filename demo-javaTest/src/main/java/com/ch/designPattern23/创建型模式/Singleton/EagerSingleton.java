package com.ch.designPattern23.创建型模式.Singleton;

/**
 * @className: EagerSingleton
 * @Auther: ch
 * @Date: 2021/5/17 18:27
 * @Description: 饿汉式单例
 */
public class EagerSingleton {

    // 一来就把对象所有东西都加载完毕,数据多的话相当耗费内存,可能浪费内存空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private EagerSingleton() {
        System.out.println(Thread.currentThread().getName());
    }

    private static final EagerSingleton instance = new EagerSingleton();

    public static EagerSingleton newInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                newInstance();
            }).start();
        }
    }

}
