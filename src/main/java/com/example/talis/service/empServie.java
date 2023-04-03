package com.example.talis.service;

import com.example.talis.mapper.empMapper;
import com.example.talis.pojo.PageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public interface empServie {

    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);
}
