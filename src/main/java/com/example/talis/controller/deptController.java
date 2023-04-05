package com.example.talis.controller;

import com.example.talis.pojo.Dept;
import com.example.talis.pojo.Result;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.talis.service.deptService;

import java.util.List;

@Slf4j
@RestController//将类型转化为json格式返回
public class deptController {
    @Autowired
    private deptService deptService;
//    private static Logger log = LoggerFactory.getLogger();

    //    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping("/depts")//限定请求方式为get
    public Result list() {
        log.info("查询全部部门的数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        log.info("根据id{}删除部门", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("根据post方式新增部门{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id为{}进行查询", id);
        List<Dept> list = deptService.select(id);
        return Result.success(list);
    }

    @PutMapping ("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("根据put方式修改部门{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}

