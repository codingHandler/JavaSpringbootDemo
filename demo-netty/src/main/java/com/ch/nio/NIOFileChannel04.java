package com.ch.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author: ch
 * @date: 2022/8/24 10:30
 * @description: 拷贝文件transferFrom方法
 */
public class NIOFileChannel04 {
    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream = new FileInputStream("d://copy.txt");
            FileOutputStream fileOutputStream = new FileOutputStream("d://copy2.txt");

            //获取各个流对应的 FileChannel
            FileChannel sourceChannel = fileInputStream.getChannel();
            FileChannel destChannel = fileOutputStream.getChannel();

            //使用 transferForm 完成拷贝
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            //关闭相关通道和流
            sourceChannel.close();
            destChannel.close();
            fileInputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
