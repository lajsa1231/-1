package com.lhy.controller;

import com.lhy.Aop.LogOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lhy.pojo.Dept;
import com.lhy.pojo.Result;
import com.lhy.service.DeptServiceimpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//表示这是一个请求控制器类
public class Deptcontroller {

    private static final Logger log = LoggerFactory.getLogger(Deptcontroller.class);

    @Autowired//表示自动装配
    private DeptServiceimpl deptServiceimpl;

    /**
     * 获取部门列表
     * @return
     */
    @GetMapping("/depts")//表示请求的路径
    public Result getDeptList() {//表示返回结果，这里返回的是部门列表
        //System.out.println("获取部门列表");
        log.info("获取部门列表");
        List<Dept> list = deptServiceimpl.findAll();
        return Result.success(list);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @LogOperation
    @DeleteMapping("/depts")
    public Result deleteDept(@RequestParam("id") Integer id) {
        log.info("删除部门" + id);
        deptServiceimpl.deleteDept(id);
        return Result.success();
    }
    /**
     * 添加部门
     * @return
     */
    @LogOperation
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept)
    {
        log.info("添加部门" + dept);
        deptServiceimpl.add(dept);
        return Result.success();
    }
    /**
     * 修改时回显部门
     * @return
     */
    @GetMapping("/depts/{id}")
    public Result getDeptById(@PathVariable Integer id)
    {
        log.info("修改时回显部门{}",id);
        Dept dept = deptServiceimpl.getDeptById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     * @return
     */
    @LogOperation
    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept)
    {
        log.info("修改部门{}",dept);
        deptServiceimpl.updateDept(dept);
        return Result.success();
    }
}

