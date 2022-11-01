package com.ch.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: ch
 * @date: 2022/8/23 21:15
 * @description: TODO
 * MappedByteBuffer可以让文件直接在内存(堆内存)修改,操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) {
        try {
            RandomAccessFile rw = new RandomAccessFile("d://file1.txt", "rw");
            FileChannel channel = rw.getChannel();
            /**
             * 参数1,FileChannel.MapMode.READ_WRITE 使用读写模式
             * 参数2,0 :可以直接修改起始位置
             * 参数3,5 :是映射到内存的大小,即将file1.txt的多少个字节映射到内存
             * 可以直接修改的范围就是0-4
             * 实际类型DirectByteBuffer
             */
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4);
            map.put(0,(byte) 'H');
            System.out.println("修改完成");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
