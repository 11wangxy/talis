package com.example.talis.service;

import com.example.talis.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;


public interface deptService {
    List<Dept> list();

    void delete(Integer id);

    void add(Dept dept);

    List<Dept> select(Integer id);

    void update(Dept dept);

}
