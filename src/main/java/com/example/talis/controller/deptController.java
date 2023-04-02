package com.example.talis.controller;

import com.example.talis.pojo.Dept;
import com.example.talis.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.talis.service.deptService;

import java.util.List;

@Slf4j
@RestController
public class deptController {
    @Autowired
    private deptService deptService;
//    private static Logger log = LoggerFactory.getLogger();

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")//限定请求方式为get
    public Result list(){
        log.info("查询全部部门的数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除id为{}的数据",id);
        deptService.delete(id);
        return Result.success();
    }
}
