package com.ch.JVM测试;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.ArrayList;

/**
 * @author: ch
 * @date: 2022/7/7 12:02
 * @description: TODO
 */
public class JVMTest {
    /***
     * //常见配置汇总
     * =================== 堆设置 ===================
     * -Xmn:
     * -Xms:初始堆大小
     * -Xmx:最大堆大小
     * -XX:NewSize=n:设置年轻代大小
     * -XX:NewRatio=n:设置年轻代和年老代的比值.如:为3,表示年轻代与年老代比值为1:3,年轻代占整个年轻代年老代和的1/4
     * -XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值.注意Survivor区有两个.如:3,表示Eden:Survivor=3:2,一个Survivor区占整个年轻代的1/5
     * -XX:MaxPermSize=n:设置持久代大小
     * =================== 收集器设置 ===================
     * -XX:+UseSerialGC:设置串行收集器
     * -XX:+UseParallelGC:设置并行收集器
     * -XX:+UseParalledlOldGC:设置并行年老代收集器
     * -XX:+UseConcMarkSweepGC:设置并发收集器
     * //垃圾回收统计信息
     * -XX:+PrintGC
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:filename
     * =================== 并行收集器设置 ===================
     * -XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数.并行收集//线程数.
     * -XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间
     * -XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比.公式为1/(1+n)
     * //并发收集器设置
     * -XX:+CMSIncrementalMode:设置为增量模式.适用于单CPU情况.
     * -XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时,使用的CPU数.并行收集线程数.
     * -XX:+CMSParallelRemarkEnabled:并发清理
     */

    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        testAllocation();

    }

    /***
     * JVM设置:
     *  -Xms5M -Xmx5M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\jvm.dump
     */
    public static void test(){
        ArrayList<Object> list = new ArrayList<>();
        while (true) {
            System.out.println("111");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new SecurityProperties.User());
        }
    }


    /*** VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }
}