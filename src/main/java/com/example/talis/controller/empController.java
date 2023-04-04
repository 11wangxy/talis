package com.example.talis.controller;

import com.example.talis.pojo.PageBean;
import com.example.talis.pojo.Result;
import com.example.talis.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.example.talis.service.empServie;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class empController {
    @Autowired
    private empServie empServie;
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询参数：{}{}{}{}{}{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean = empServie.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("执行批量删除，id值为{}",ids);
        empServie.delete(ids);
        return Result.success();
    }
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工{}",emp);
        empServie.save(emp);
        return Result.success();
    }
    @GetMapping ("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询{}",id);
        Emp emps= empServie.getById(id);
        return Result.success(emps);
    }
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新数据{}",emp);
        empServie.update(emp);
        return Result.success();
    }

}
