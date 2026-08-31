package com.lhy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhy.mapper.EmpMapper;
import com.lhy.mapper.LogMapper;
import com.lhy.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceimlp implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private LogMapper logMapper;

    @Override
    public JobOption getempjobdata() {
        List<Map<String, Object>> empjobdata = empMapper.countEmpjobdata();
        List<Object> jobList = empjobdata.stream().map(mapdata->mapdata.get("pos")).toList();
        List<Object> dataList = empjobdata.stream().map(mapdata->mapdata.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getempgenderdata() {
        return empMapper.countEmpgenderdata();
    }

    @Override
    public List<Map<String, Object>> getstudentdegreedata() {
        return empMapper.countStudentdegreedata();
    }

    @Override
    public ClassOption getclassstudentdata() {
        List<Map<String, Object>> clazzList = empMapper.countClassstudentdata();
        List<Object> clazzList1 = clazzList.stream().map(mapdata->mapdata.get("name")).toList();
        List<Object> dataList1 = clazzList.stream().map(mapdata->mapdata.get("value")).toList();
        return new ClassOption(clazzList1, dataList1);
    }

    @Override
    public PageResult<OperateLog> getlog(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> list = logMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
