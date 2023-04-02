package com.example.talis.service.impl;
import com.example.talis.pojo.Dept;
import com.example.talis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.talis.mapper.deptMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class deptServiceImpl implements deptService{
    @Autowired
    private deptMapper deptMapper;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }
}
