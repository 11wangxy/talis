package com.example.talis.mapper;

import com.example.talis.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface empMapper {
//    @Select("select count(*) from emp")
//    public long count();
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);
    //分页查询，使用pagehelper插件
    // @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);
    @Insert("insert into test.emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
    @Select("select * from test.emp where id=#{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from test.emp where username=#{username} and password=#{password}")
    Emp getByUsername(Emp emp);
//根据部门id删除该部门下的员工
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);
}
