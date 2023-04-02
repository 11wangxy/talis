package com.example.talis.mapper;

import com.example.talis.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface deptMapper {
    @Select("select * from dept ")
    List<Dept> list();
    @Delete("delete from dept where id=#{}")
    void deleteById(Integer id);
}
