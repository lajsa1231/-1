package com.lhy.controller;

import com.lhy.pojo.*;
import com.lhy.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 获取员工职位数据
     * @return
     */
    @GetMapping("/report/empJobData")
    public Result getempjobdata()
    {
        log.info("获取员工职位数据");
        JobOption jobOption = reportService.getempjobdata();
        return Result.success(jobOption);
    }


    @GetMapping("/report/empGenderData")
    public Result getempgenderdata()
    {
        log.info("获取员工性别数据");
        List<Map<String, Object>> list = reportService.getempgenderdata();
        return Result.success(list);
    }
    @GetMapping("/report/studentDegreeData")
    public Result getstudentdegreedata()
    {
        log.info("获取学生学历数据");
        List<Map<String, Object>> list = reportService.getstudentdegreedata();
        return Result.success(list);
    }

    @GetMapping("/report/studentCountData")
    public Result getclassstudentdata()
    {
        log.info("获取班级学生数据");
        ClassOption classOption = reportService.getclassstudentdata();
        return Result.success(classOption);
    }

    @GetMapping("/log/page")
    public Result getlog(Integer page,Integer pageSize)
    {
        log.info("查询修改日志");
        PageResult<OperateLog> pageResult = reportService.getlog(page, pageSize);
        return Result.success(pageResult);
    }
}
