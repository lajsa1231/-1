package com.lhy.Aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Aspect1 {

    @Before("execution(* com.lhy.service.*.u*(..))")
    public void before(){
        log.info("Aspect1 before");
    }
}
