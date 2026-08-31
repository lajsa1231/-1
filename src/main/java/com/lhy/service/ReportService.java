package com.lhy.service;

import com.lhy.pojo.ClassOption;
import com.lhy.pojo.JobOption;
import com.lhy.pojo.OperateLog;
import com.lhy.pojo.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {

    JobOption getempjobdata();

    List<Map<String, Object>> getempgenderdata();

    List<Map<String, Object>> getstudentdegreedata();

    ClassOption getclassstudentdata();

    PageResult<OperateLog> getlog(Integer page,Integer pagesize);
}
