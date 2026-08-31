package com.lhy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhy.exception.ViolationException;
import com.lhy.mapper.StudentMapper;
import com.lhy.pojo.Clazz;
import com.lhy.pojo.PageResult;
import com.lhy.pojo.Student;
import com.lhy.pojo.StudentQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> queryStudent(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.list(studentQueryParam);
        Page<Student> page = (Page<Student>)list;
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short)0);
        student.setViolationScore((short)0);
        studentMapper.addStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(List<Integer> ids) {
        studentMapper.deleteStudents(ids);
    }

    @Override
    public void updateStudentViolation(Integer id, Integer score) {
         if(score <= 0 )
         {
             throw new ViolationException("违规分数必须大于0");
         }
         Student student = studentMapper.getStudentById(id);
         student.setViolationCount((short)(student.getViolationCount() + 1));
         student.setViolationScore((short)(student.getViolationScore() + score));
         student.setUpdateTime(LocalDateTime.now());
         studentMapper.updateStudentViolation(student);
    }
}
