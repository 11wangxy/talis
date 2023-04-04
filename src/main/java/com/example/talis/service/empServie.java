package com.example.talis.service;

import com.example.talis.pojo.Emp;
import com.example.talis.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface empServie {

    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);
    //emp表批量删除
    void delete(List<Integer> ids);//接受删除id的员工

    void save(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}
