package com.lhy.controller;

import com.lhy.Aop.LogOperation;
import com.lhy.pojo.Emp;
import com.lhy.pojo.EmpQuaryParam;
import com.lhy.pojo.PageResult;
import com.lhy.pojo.Result;
import com.lhy.service.EmpServiceimpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 员工管理控制器
 */
@Slf4j
@RestController
public class Empcontroller {

    @Autowired
    private EmpServiceimpl empServiceimpl;

    /**
     * 分页查询
     *
     * @return
     */
//    @GetMapping("/emps")
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pagesize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        log.info("页码{}，每页展示数量{}, 姓名{}, 年龄{}, 开始日期{}, 结束日期{}",page,pagesize,name,gender,begin,end);
//        PageResult<Emp> pageResult = empServiceimpl.page(page,pagesize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    @GetMapping("/emps")
    public Result page(EmpQuaryParam empQuaryParam) {
        log.info("相关信息{}", empQuaryParam);
        PageResult<Emp> pageResult = empServiceimpl.page(empQuaryParam);
        return Result.success(pageResult);
    }
    @LogOperation
    @PostMapping("/emps")
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工{}", emp);
        empServiceimpl.save(emp);
        return Result.success();
    }
    @LogOperation
    @DeleteMapping("/emps")
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工{}", ids);
        empServiceimpl.delete(ids);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询员工{}", id);
        Emp emp = empServiceimpl.get(id);
        return Result.success(emp);
    }
    @LogOperation
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工 {}", emp);
        empServiceimpl.update(emp);
        return Result.success();
    }
    @GetMapping("/emps/list")
    public Result list() {
        log.info("查询员工");
        List<Emp> list= empServiceimpl.getList();
        return Result.success(list);
    }
}
