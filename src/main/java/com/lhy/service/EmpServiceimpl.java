package com.lhy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhy.mapper.EmpExprMapper;
import com.lhy.mapper.EmpMapper;
import com.lhy.pojo.*;
import com.lhy.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceimpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    //分页查询原始方法实现
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pagesize) {
//        //获取页数
//        long count = empMapper.count();
//        //获取每页数据
//        List<Emp> list = empMapper.list((page-1)*pagesize,pagesize);
//        return new PageResult<Emp>(count,list);
//    }

    /**
     * 添加员工
     *
     * @param emp
     */
    @Transactional // 事务管理，确保数据的一致性
    @Override
    public void save(Emp emp) {
        //保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.Insert(emp);
        //保存员工工作经历
        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)) {
            //遍历集合，给每一条工作经历设置员工id
            empExprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            // ✅循环结束后，一次性批量插入全部数据
            empExprMapper.InsertExprlist(empExprList);
        }
    }

    /**
     * 分页查询使用PageHelper实现
     * @param empQuaryParam
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQuaryParam empQuaryParam) {
        PageHelper.startPage(empQuaryParam.getPage(), empQuaryParam.getPagesize());
        List<Emp> list = empMapper.list(empQuaryParam);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 删除员工
     * @param ids
     */

    @Override
    public void delete(List<Integer> ids) {
        //删除员工基本信息
        empMapper.deleteByIds(ids);
        //删除员工工作经历
        empExprMapper.deleteEmpByEmpids(ids);
    }

    /**
     * 在修改员工信息前查询员工基本信息
     *
     * @param id
     * @return
     */
    @Override
    public Emp get(Integer id) {
        return empMapper.get(id);
    }
    /**
     * 修改员工信息
     * @param emp
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //修改员工基本信息
        empMapper.update(emp);
        //删除员工工作经历
        empExprMapper.deleteEmpByEmpids(Arrays.asList(emp.getId()));
        //加入员工工作经历
        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)) {
            //遍历集合，给每一条工作经历设置员工id
            empExprList.forEach(expr -> {
                expr.setEmpId(emp.getId());
            });
            // ✅循环结束后，一次性批量插入全部数据
            empExprMapper.InsertExprlist(empExprList);
        }
    }

    @Override
    public List<Emp> getList() {
        return empMapper.getEmp();
    }

    @Override
    public Logininfo login(Emp emp) {
        //调用Mapper接口
        Emp e = empMapper.login(emp);
        //判断这个员工是否存在
        if(e != null)
        {
            Map<String, Object> claims =new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateJwt(claims);
            return new Logininfo(e.getId(), e.getName(), e.getUsername(), token);
        }
        return null;
    }
}
