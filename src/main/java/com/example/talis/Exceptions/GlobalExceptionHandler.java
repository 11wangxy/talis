package com.example.talis.Exceptions;

import com.example.talis.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//全局异常处理器
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.error("操作失败");
    }
}
