package com.lhy.exception;

import com.lhy.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.lhy.exception.ViolationException;


@Slf4j
@RestControllerAdvice // 统一处理异常
public class exceptionhandle {

    @ExceptionHandler // 处理异常
    public Result handleException(Exception e) {
        log.error("出现异常：{}", e);
        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler
    public Result handleException2(DuplicateKeyException e) {
        log.error("出现异常：{}", e);
        int i = e.toString().indexOf("Duplicate entry");
        String message = e.toString().substring(i);
        String [] arr = message.split(" ");
        String phone = arr[2];
        return Result.error(phone+"重复了");
    }
    @ExceptionHandler
    public Result handleDeleteHasStudentException(DeleteHasStudentException e){
        log.error("出现异常：{}", e);
        return Result.error("删除失败，该班级下有学生");
    }
    @ExceptionHandler
    public Result handleViolationException(ViolationException e){
        log.error("出现异常：{}", e);
        return Result.error("添加失败，违规扣分要大于0");
    }
}
