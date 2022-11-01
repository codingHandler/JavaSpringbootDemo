package com.ch.designPattern23.创建型模式.Singleton;

/**
 * @className: LazySingleton
 * @Auther: ch
 * @Date: 2021/5/17 18:27
 * @Description: 懒汉式单例
 */
public class LazySingleton {

    private volatile static LazySingleton instance = null;

    private LazySingleton() {
        System.out.println(Thread.currentThread().getName());
    }

    // 双层检测锁模式的懒汉式模式 简称DCL懒汉式(Double Check Lock)
    public static LazySingleton newInstance() {
        if (instance==null){
            synchronized(LazySingleton.class){
                if (instance == null) {
                    instance = new LazySingleton(); // 不是一个原子性操作
                    /**
                     * new 一个对象底层执行的操作:
                     * 1,分配内存空间
                     * 2,执行构造方法,初始化对象
                     * 3,把这个对象指向这个空间
                     * 在这个过程中,JVM可以自由的进行指令重排序的优化,由于指令重排的优化
                     * 导致初始化Singleton对象和将对象地址赋给instance字段的顺序是不确定的。
                     * 如有可能出现:1->2->3 也有可能出现1->3-2 的顺序
                     *
                     * 此时在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值。
                     * 此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没有初始化。
                     * 若紧接着另外一个线程来调用getInstance，取到的就是状态不正确的对象，程序就会出错。
                     *
                     * 为解决双重检测锁懒汉式单例的隐患:
                     * 可以使用volatile特殊修饰符:
                     * 注意:
                     * volatile是一个特殊的修饰符，只有成员变量才能使用它。
                     * volatile的作用:
                     * (1):多线程主要围绕可见性和原子性两个特性而展开，
                     * 使用volatile关键字修饰的变量，保证了其在多线程之间的可见性，
                     * 即每次读取到volatile变量，一定是最新的数据
                     * (2):使用volatile则会对禁止语义重排序，当然这也一定程度上降低了代码执行效率
                     **/
                }
            }
        }
        return instance;
    }
    // 单线程单例ok
    // 多线程并发
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                newInstance();
            }).start();
        }
    }
}

