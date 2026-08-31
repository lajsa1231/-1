package com.lhy.service;

import com.lhy.pojo.PageResult;
import com.lhy.pojo.Student;
import com.lhy.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> queryStudent(StudentQueryParam studentQueryParam);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudent(List<Integer> ids);

    void updateStudentViolation(Integer id, Integer score);
}
