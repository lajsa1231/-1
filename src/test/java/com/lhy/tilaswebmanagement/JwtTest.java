package com.lhy.tilaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {
    @Test
    public void testJwt1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lhy");
        map.put("id", "1");
        String jwt = Jwts.builder()
                         .signWith(SignatureAlgorithm.HS256, "bGh5")// 设置签名算法和密钥
                         .addClaims(map)// 添加声明
                         .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))// 设置过期时间
                         .compact();
        System.out.println(jwt);
    }
    @Test
    public void testJwt2() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoibGh5IiwiaWQiOiIxIiwiZXhwIjoxNzg3MDcwNjExfQ.K_jxyH7I08CHgvW1lE9kYsgEKNDRLjiLiC5D4JyxAk4";
        Claims claims = Jwts.parser()
                            .setSigningKey("bGh5")//设置密钥
                            .parseClaimsJws(token)//解析token
                            .getBody();//获取claims
        System.out.println(claims);
    }
}
