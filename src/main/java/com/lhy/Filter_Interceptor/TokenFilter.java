package com.lhy.Filter_Interceptor;

import com.lhy.utils.CurrentHolder;
import com.lhy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        //1.获取url
        String url = req.getRequestURI();
        //2.判断是否为登录操作
        if(url.contains("login"))
        {
            log.info("登录操作，不进行令牌检查");
            chain.doFilter(request, response);
            return;
        }
        //3.获得请求头中的令牌
        String token = req.getHeader("token");
        //4.判断令牌是否为空
        if(token == null || token.isEmpty())
        {
            log.info("令牌为空");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.判断令牌是否合法
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer userId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(userId);
            log.info("令牌合法，用户ID：{}", userId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌非法");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.放行
        log.info("令牌合法，放行");
        chain.doFilter(request, response);
        CurrentHolder.remove();
    }
}
