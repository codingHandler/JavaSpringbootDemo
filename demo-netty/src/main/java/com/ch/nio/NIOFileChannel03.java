package com.ch.nio;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: ch
 * @date: 2022/8/24 09:47
 * @description: 使用channel拷贝文件
 */
public class NIOFileChannel03 {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File("d://file1.txt");
            fileInputStream = new FileInputStream(file);
            // 获取输入流通道
            FileChannel inputChannel = fileInputStream.getChannel();

            fileOutputStream = new FileOutputStream("d://copy.txt",true);
            // 获取输出流通道
            FileChannel outputChannel = fileOutputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 循环读取数据
            while (true) {
                int read = inputChannel.read(buffer);
                if (read == -1) {
                    System.out.println("拷贝完成!");
                    break;
                }
                // 切换为读模式
                buffer.flip();
                System.out.println("正在拷贝...");
                outputChannel.write(buffer);
                buffer.flip();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
