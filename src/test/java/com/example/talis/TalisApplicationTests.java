package com.example.talis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TalisApplicationTests {

    @Test//生成jwt令牌
    public void testjwt(){
        Map<String, Object> Claims=new HashMap<>();
        Claims.put("id",1);
        Claims.put("name","tom");
        String gdufe = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "GDUFE")//签名算法
                .setClaims(Claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(gdufe);
    }
    @Test//校验jwt令牌
    public void testParsejwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("GDUFE")//指定签名秘钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MDcwMzcxMX0.O4C4IFrzs5t6wyYNLJG7Hvxz9LDXg36bqAfMcpyNd8o")
                .getBody();
        System.out.println(claims);
    }
}
