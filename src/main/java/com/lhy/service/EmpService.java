package com.lhy.service;

import com.lhy.pojo.Emp;
import com.lhy.pojo.EmpQuaryParam;
import com.lhy.pojo.Logininfo;
import com.lhy.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQuaryParam empQuaryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp get(Integer id);

    void update(Emp emp);

    List<Emp> getList();

    Logininfo login(Emp emp);
}
