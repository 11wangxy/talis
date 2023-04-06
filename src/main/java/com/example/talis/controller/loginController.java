package com.example.talis.controller;
import com.example.talis.pojo.Emp;
import com.example.talis.pojo.Result;
import com.example.talis.service.empServie;
import com.example.talis.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class loginController {
    @Autowired
    private empServie empServie;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){//@requestbody负责吧json文件格式转化为实体类
        log.info("员工登录{}",emp);
        Emp e = empServie.login(emp);
        //登录成功，生成jwt令牌，下发令牌
        if(e!=null){
            Map<String,Object> claims =new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateJwt(claims);
            log.info("生成的jwt令牌：\n{}",jwt);
            return Result.success(jwt);
        }
        else { return Result.error("用户名或者密码错误");}
        //登录失败返回信息
    }
}
