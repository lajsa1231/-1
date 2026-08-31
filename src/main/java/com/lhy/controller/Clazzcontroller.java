package com.lhy.controller;

import com.lhy.pojo.Clazz;
import com.lhy.pojo.ClazzQueryParam;
import com.lhy.pojo.PageResult;
import com.lhy.pojo.Result;
import com.lhy.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Clazzcontroller {
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/clazzs")
    public Result queryClazzs(ClazzQueryParam params) {
        log.info("查询班级参数: {}", params.toString());
        PageResult<Clazz> pageResult = clazzService.queryClazzs(params) ;
        return Result.success(pageResult);
    }

    @PostMapping("/clazzs")
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("新增班级参数: {}", clazz.toString());
        clazzService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/clazzs/{id}")
    public Result queryClazzById(@PathVariable Integer id) {
        log.info("查询班级参数: {}", id);
        Clazz clazz = clazzService.queryClazzById(id);
        return Result.success(clazz);
    }
    @PutMapping("/clazzs")
    public Result putclazz(@RequestBody Clazz clazz) {
        log.info("修改班级参数: {}", clazz.toString());
        clazzService.putclazz(clazz);
        return Result.success();
    }
    @DeleteMapping("/clazzs/{id}")
    public Result deleteClazz(@PathVariable Integer id) {
        log.info("删除班级参数: {}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    @GetMapping("/clazzs/list")
    public Result queryClazzList() {
        log.info("查询班级列表");
        List<Clazz> clazzList = clazzService.queryClazzList();
        return Result.success(clazzList);
    }
}
