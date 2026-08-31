package com.lhy.mapper;

import com.lhy.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper{
    @Select("SELECT operate_log.id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time, emp.name as operateEmpName FROM operate_log left join emp on operate_log.operate_emp_id = emp.id ")
    List<OperateLog> list();
}
