package com.example.talis.controller;

import com.example.talis.pojo.Result;
import com.example.talis.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class uploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
//    //存储在本地
//    @PostMapping
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception{
//        log.info("文件上传{}{}{}",username,age,image);
//        //把接受的文件传入到本地存储
//        //构造当前唯一文件名，防止覆盖--uuid
//        String originalFilename = image.getOriginalFilename();//获得文件初始名
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString()+extname;
//        image.transferTo(new File("D:\\"+newFileName));
//        return Result.success();
//    }
    //存储云服务
    @PostMapping
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传{}",image.getOriginalFilename());
        //调用阿里云oss工具类
        String url = aliOSSUtils.upload(image);
        log.info("文件已经上传完毕");
        return Result.success(url);
    }
}
