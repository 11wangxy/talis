package com.example.talis.service.impl;
import com.example.talis.pojo.Emp;
import com.example.talis.pojo.PageBean;
import com.example.talis.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.talis.mapper.empMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class empServiceImpl implements empServie{
    @Autowired
    private empMapper empMapper;
    //分页查询
//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        long count = empMapper.count();
//        Integer start = (page-1)*pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        //封装对象
//        PageBean pageBean = new PageBean(count, empList);
//        return pageBean;
//    }
    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //设置分页参数
        PageHelper.startPage(page,pageSize);
        //执行操作
        List<Emp> empList = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) empList;
        //封装对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }
}
