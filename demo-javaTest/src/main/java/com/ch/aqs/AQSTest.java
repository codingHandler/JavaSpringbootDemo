package com.ch.aqs;


import com.sun.corba.se.impl.orbutil.concurrent.Sync;

/**
 * @author: ch
 * @date: 2022/9/15 17:06
 * @description: AQS
 * AQS同步器是基于模板模式设计的
 */
public class AQSTest {
    public static void main(String[] args) {
        Sync sync = new Sync() {
            @Override
            public void acquire() throws InterruptedException {

            }

            @Override
            public boolean attempt(long msecs) throws InterruptedException {
                return false;
            }

            @Override
            public void release() {

            }
        };
    }
}
