package com.lhy.Filter_Interceptor;

import com.lhy.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取url
        String url = request.getRequestURI();
        //2.判断是否为登录操作
        if(url.contains("login"))
        {
            log.info("登录操作，不进行令牌检查");
            return true;
        }
        //3.获得请求头中的令牌
        String token = request.getHeader("token");
        //4.判断令牌是否为空
        if(token == null || token.isEmpty())
        {
            log.info("令牌为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.判断令牌是否合法
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌非法");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.放行
        log.info("令牌合法，放行");
        return true;
    }
}
