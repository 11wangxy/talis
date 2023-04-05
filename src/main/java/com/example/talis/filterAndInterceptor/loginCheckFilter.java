package com.example.talis.filterAndInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.talis.pojo.Result;
import com.example.talis.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/*")
public class loginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;

        //1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("url:{}",url);
        //2.判断请求是否包含login，如果包含则是登录操作，进行放行，否则拦截
        if(url.contains("login")){
            log.info("login");
            chain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = req.getHeader("token");
        //4.判断令牌是否存在，不存在则返回错误结果
        if(!StringUtils.hasLength(token)){
            log.info("token is null");
            Result error = Result.error("NOT_LOGIN");
            //手动转化json格式传回去，因为不在controller层，故无法调用restcontroller，需要手动转化
            String s = JSONObject.toJSONString(error);
            resp.getWriter().write(s);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {//解析失败,返回错误信息
            e.printStackTrace();
            log.info("fail to parse");
            Result error = Result.error("NOT_LOGIN");
            //手动转化json格式传回去，因为不在controller层，故无法调用restcontroller，需要手动转化
            String s = JSONObject.toJSONString(error);
            resp.getWriter().write(s);
            return;
        }
        //6.放行
        log.info("jwt pass");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
