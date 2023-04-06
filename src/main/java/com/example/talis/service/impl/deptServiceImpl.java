package com.example.talis.service.impl;
import com.example.talis.mapper.empMapper;
import com.example.talis.pojo.Dept;
import com.example.talis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.talis.mapper.deptMapper;
import org.springframework.stereotype.Service;
import com.example.talis.mapper.empMapper;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class deptServiceImpl implements deptService{
    @Autowired
    private deptMapper deptMapper;
    @Autowired
    private empMapper empMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
    @Transactional(rollbackFor = Exception.class)//spring开启事务 rollbackfor代表出现所有异常都回滚
    @Override
    //根据id删除部门，为了保证事务一致性，需要把该部门员工也删除
    public void delete(Integer id) {
        deptMapper.deleteById(id);//删除部门
        empMapper.deleteByDeptId(id);//删除员工
    }


    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public List<Dept> select(Integer id) {
        return deptMapper.select(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}


