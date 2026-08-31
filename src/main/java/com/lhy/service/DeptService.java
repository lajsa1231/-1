package com.lhy.service;

import com.lhy.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> findAll();
    void deleteDept(Integer id);
    void add(Dept dept);

    Dept getDeptById(Integer id);

    void updateDept(Dept dept);
}
