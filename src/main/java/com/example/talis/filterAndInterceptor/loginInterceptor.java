package com.example.talis.filterAndInterceptor;


import com.alibaba.fastjson.JSONObject;
import com.example.talis.pojo.Result;
import com.example.talis.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class loginInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前，如果返回t，放行，否则f为拦截
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求url
        String url = req.getRequestURI().toString();
        log.info("url:{}",url);
        //2.判断请求是否包含login，如果包含则是登录操作，进行放行，否则拦截
        if(url.contains("login")){
            log.info("login放行");
            return true;
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
            return false;
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
            return false;
        }
        //6.放行
        log.info("jwt pass");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion..");
    }
}
