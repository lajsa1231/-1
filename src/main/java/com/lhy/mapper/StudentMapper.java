package com.lhy.mapper;

import com.lhy.pojo.Student;
import com.lhy.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    public Integer countStudentByClazzId(Integer clazzId);

    List<Student> list(StudentQueryParam studentQueryParam);

    void addStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudents(List<Integer> ids);

    void updateStudentViolation(Student student);
}
