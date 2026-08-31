package com.lhy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhy.exception.DeleteHasStudentException;
import com.lhy.mapper.ClazzMapper;
import com.lhy.mapper.StudentMapper;
import com.lhy.pojo.Clazz;
import com.lhy.pojo.ClazzQueryParam;
import com.lhy.pojo.Emp;
import com.lhy.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceimpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 根据查询条件分页查询班级信息
     * @param params
     * @return
     */
    @Override
    public PageResult<Clazz> queryClazzs(ClazzQueryParam params) {
        PageHelper.startPage(params.getPage(), params.getPageSize());
        List<Clazz> list = clazzMapper.list(params);
        list.stream().forEach(e -> {
            if(e.getEndDate().isBefore(LocalDate.now()))
            {e.setStatus("已结业");}
            else if (e.getBeginDate().isBefore(LocalDate.now())) {
             e.setStatus("进行中");
            }else{
                e.setStatus("未开始");
            }
        });
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.add(clazz);
    }

    @Override
    public Clazz queryClazzById(Integer id) {
        return clazzMapper.queryClazzById(id);
    }

    @Override
    public void putclazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.putclazz(clazz);
    }

    @Override
    public void deleteClazz(Integer id) {
        Integer count = studentMapper.countStudentByClazzId(id);
        if(count > 0){
            // 有学生，抛自定义异常
            throw new DeleteHasStudentException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteClazz(id);
    }

    @Override
    public List<Clazz> queryClazzList() {
        return clazzMapper.queryClazzList();
    }
}
