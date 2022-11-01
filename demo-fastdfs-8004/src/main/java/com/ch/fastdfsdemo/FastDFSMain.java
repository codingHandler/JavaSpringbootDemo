package com.ch.fastdfsdemo;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @className: FastDFSMain
 * @Auther: ch
 * @Date: 2022/4/2 09:42
 * @Description: springboot整合fastDFS demo
 */
@Import(FdfsClientConfig.class)
@SpringBootApplication
public class FastDFSMain {
    public static void main(String[] args) {
        SpringApplication.run(FastDFSMain.class, args);
    }
}
