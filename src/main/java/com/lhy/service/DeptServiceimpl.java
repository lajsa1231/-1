package com.lhy.service;

import com.lhy.Aop.LogOperation;
import com.lhy.mapper.DeptMapper;
import com.lhy.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceimpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteDept(Integer id) {
        deptMapper.deleteDept(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(java.time.LocalDateTime.now());
        dept.setUpdateTime(java.time.LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
       return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(java.time.LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
