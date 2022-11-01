package com.ch.nio.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @className: TestByteBuffer
 * @Auther: ch
 * @Date: 2022/3/31 15:27
 * @Description: TODO
 */
@Slf4j
public  class TestByteBuffer {
    public static void main(String[] args) throws FileNotFoundException {
        // FileChannel
        // 1,输入输出流 2,RandomAccessFile
        try(FileChannel channel = new FileInputStream("F:\\companyItem\\JavaSpringbootDemo\\demo-netty\\data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // 从channel读取数据,向buffer写入
            while (true){
                int len = channel.read(buffer);
                log.info("读取到的字节数:{}",len);
                if(len==-1){
                   break;
                }
                buffer.flip(); // 切换至读模式
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    // 打印buffer的内容
                    log.info("实际字节为:{} ",String.valueOf((char) b));
                }
                //buffer.clear(); // 切换为写模式
                buffer.compact(); // 切换为写模式,区别:如果一次没有读完byte数组中的值,会保留未读取数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
