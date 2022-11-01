package com.ch.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: ch
 * @date: 2022/8/23 17:45
 * @description: TODO
 */
public class NIOFileChannel {
    public static void main(String[] args) {
        String str = "hello,爪哇岛";
        try {
            // 创建一个输出流->Channel
            FileOutputStream fileOutputStream = new FileOutputStream("d://file1.txt");

            // fileChannel真实类型是FileChannelImpl
            FileChannel fileChannel = fileOutputStream.getChannel();

            // 创建一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 将str放入byteBuffer
            buffer.put(str.getBytes());

            // 对byteBuffer进行反正flip
            buffer.flip();

            // 将byteBuffer数据写入fileChannel
            fileChannel.write(buffer);
            // 关闭流
            fileOutputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
