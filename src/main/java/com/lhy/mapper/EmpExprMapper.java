package com.lhy.mapper;

import com.lhy.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    public void InsertExprlist(List<EmpExpr> list);

    void deleteEmpByEmpids(List<Integer> Empids);
}
