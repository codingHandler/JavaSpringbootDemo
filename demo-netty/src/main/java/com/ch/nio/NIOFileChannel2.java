package com.ch.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: ch
 * @date: 2022/8/23 18:06
 * @description: TODO
 */
public class NIOFileChannel2 {
    public static void main(String[] args) {
        try {
            File file = new File("d://file1.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            // 获取FileChannel
            FileChannel channel = fileInputStream.getChannel();
            // 创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            while (true) {
                // 清空buffer
                byteBuffer.clear();
                // 将通道数据读入到缓冲区
                int read = channel.read(byteBuffer);
                if (read == -1) { // 表示读完
                    break;
                }
                // 将byteBuffer的字节数据转成String
                System.out.println(new String(byteBuffer.array()));
            }


            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
