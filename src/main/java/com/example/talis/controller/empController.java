package com.example.talis.controller;

import com.example.talis.pojo.PageBean;
import com.example.talis.pojo.Result;
import com.example.talis.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.talis.service.empServie;

import java.time.LocalDate;

@RestController
@Slf4j
public class empController {
    @Autowired
    private empServie empServie;
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询参数：{}{}{}{}{}{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empServie.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

}
