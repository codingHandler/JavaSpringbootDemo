package com.ch.designPattern23.创建型模式.Singleton;

/**
 * @className: StaticInnerSingleton
 * @Auther: ch
 * @Date: 2021/5/18 12:20
 * @Description: 静态内部类实现单例
 */
public class StaticInnerSingleton {

    private StaticInnerSingleton() {
    }

    public static class SingletonHolder {
        private static final SingletonHolder instance = new SingletonHolder();
    }

    public static SingletonHolder getInstance() {
        return SingletonHolder.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                getInstance();
            }).start();
        }
    }
}
