package com.example.talis.service;

import com.example.talis.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface deptService {
    List<Dept> list();

    void delete(Integer id);
}
