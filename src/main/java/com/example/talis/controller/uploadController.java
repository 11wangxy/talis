package com.example.talis.controller;

import com.example.talis.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class uploadController {
    @PostMapping
    public Result upload(String username, Integer age, MultipartFile image) throws Exception{
        log.info("文件上传{}{}{}",username,age,image);
        //把接受的文件传入到本地存储
        //构造当前唯一文件名，防止覆盖--uuid
        String originalFilename = image.getOriginalFilename();//获得文件初始名
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString()+extname;
        image.transferTo(new File("D:\\"+newFileName));
        return Result.success();
    }
}
