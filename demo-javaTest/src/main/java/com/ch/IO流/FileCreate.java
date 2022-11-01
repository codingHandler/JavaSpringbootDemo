package com.ch.IO流;

import java.io.File;
import java.io.IOException;

/**
 * @author: ch
 * @date: 2022/8/15 21:06
 * @description: 创建文件的三种方式
 * new File(String pathName) 根据路径构建一个file对象
 * new File(File parent,String child) 根据父目录文件 + 子路径构建
 * new File(String parent,String child) 更据父目录路径 + 子路径构建
 */
public class FileCreate {
    public static void main(String[] args) {
        createFile1();
        createFile2();
        createFile3();
    }

    private static void createFile3() {
        try {
            File file = new File("F:\\companyItem\\JavaSpringbootDemo\\demo-javaTest\\src\\main\\resources\\createFile1.txt");
            file.createNewFile();
            System.out.println("创建file1完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile2() {
        try {
            File file = new File("F:\\companyItem\\JavaSpringbootDemo\\demo-javaTest\\src\\main\\resources");
            File file1 = new File(file, "createFile2.txt");
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFile1() {
        try {
            String parent = "F:\\companyItem\\JavaSpringbootDemo\\demo-javaTest\\src\\main\\resources";
            File file1 = new File(parent, "createFile3.txt");
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
