package com.example.talis.AOP;

import com.alibaba.fastjson.JSONObject;
import com.example.talis.mapper.OperateLogMapper;
import com.example.talis.pojo.OperateLog;
import com.example.talis.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class logAop {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.example.talis.anno.log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.获得操作人id，通过获得jwt解析得到
        //通过请求头获得token值得到jwt值
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer)claims.get("id");

        //2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //3.操作类和方法名称
        String classname = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        //4.操作方法参数
        Object[] args =joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用目标方法运行
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        //5.方法返回值
        String returnValue = JSONObject.toJSONString(result);

        Long costTime = end-begin;
        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,methodName,
                classname,methodName,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("\nAOP记录操作日志{}",operateLog);
        return result;
    }
}
