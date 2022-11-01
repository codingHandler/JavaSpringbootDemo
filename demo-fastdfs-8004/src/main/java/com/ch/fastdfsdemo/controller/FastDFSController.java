package com.ch.fastdfsdemo.controller;

import com.ch.common.domain.Result;
import com.ch.fastdfsdemo.utils.FastFileStorageClientUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @className: FastDController
 * @Auther: ch
 * @Date: 2022/4/2 10:36
 * @Description: controller层使用工具类
 */
@RestController
@RequestMapping("/files")
public class FastDFSController {
    @Autowired
    private FastFileStorageClientUtil dfsClient;


    /**
     * 上传图片并生成缩略图
     *
     * @param file
     * @return
     */
    @PostMapping("/fdfsUploadImage")
    public Result fdfsUploadImage(@RequestParam("file") MultipartFile file) {
        String fileUrl = dfsClient.uploadImageAndCrtThumbImage(file);
        return Result.success(fileUrl);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/fdfsUploadFile")
    public Result fdfsUploadFile(@RequestParam("file") MultipartFile file) {
        String fileUrl = dfsClient.uploadFileWithOutMetaData(file);
        return Result.success(fileUrl);
    }


    /**
     * 下载文件
     * 请求示例地址：http://localhost/download?filePath=group1/M00/00/00/n0v742JHsYiAEKUtAAAXBw8jR_w585.png
     *
     * @param filePath
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(String filePath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // group1/M00/00/00/wKgIZVzZEF2ATP08ABC9j8AnNSs744.jpg
        String[] paths = filePath.split("/");
        String groupName = null;
        for (String item : paths) {
            if (item.indexOf("group") != -1) {
                groupName = item;
                break;
            }
        }
        String path = filePath.substring(filePath.indexOf(groupName) + groupName.length() + 1);
        //输入流
        InputStream input = dfsClient.download(groupName, path);

        String fileName = paths[paths.length - 1];

        //设置 response的响应头  能跳出下载窗口
        //1.设置响应类型：
        //获取文件的mime类型：content-type
        String mimeType = request.getServletContext().getMimeType(fileName);
        response.setHeader("content-type", mimeType);

        //2.设置响应头打开方式：content-disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        //获取绑定了客户端的流
        ServletOutputStream output = response.getOutputStream();

        // 把输入流中的数据写入到输出流中
        IOUtils.copy(input, output);
        input.close();
    }

    /**
     * 删除文件
     * 请求地址示例：
     * http://localhost/deleteFile?filePath=group1/M00/00/00/n0v742JHsYiAEKUtAAAXBw8jR_w585.png
     *
     * @param filePath
     * @return
     */
    @GetMapping("/deleteFile")
    public Result delFile(String filePath) {
        dfsClient.delFile(filePath);
        return Result.success("文件删除成功");
    }
}
