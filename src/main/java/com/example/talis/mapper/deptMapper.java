package com.example.talis.mapper;

import com.example.talis.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface deptMapper {
    @Select("select * from dept ")
    List<Dept> list();
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime}) ")
    void insert(Dept dept);
    @Select("select * from dept where id=#{id}")
    List<Dept> select(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updates(Dept dept);
}
