package com.ch.fastdfsdemo;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * @className: SpringbootFastdfsDemoApplicationTests
 * @Auther: ch
 * @Date: 2022/4/2 10:03
 * @Description: fastdfs 测试
 */

@SpringBootTest
public class SpringbootFastdfsDemoApplicationTests {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Test
    public void contextLoads() throws FileNotFoundException {
        File file = new File("F:\\一些图片\\001.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        Set<MetaData> metaDataSet = new HashSet<>();
        MetaData metaData = new MetaData();
        metaData.setName("author");
        metaData.setValue("哈哈hhh");
        metaDataSet.add(metaData);
        StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream,
                        file.length(),
                        "png",
                        metaDataSet);
        System.out.println(storePath);
    }
}
