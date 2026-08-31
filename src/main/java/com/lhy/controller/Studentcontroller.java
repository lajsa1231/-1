package com.lhy.controller;

import com.lhy.pojo.PageResult;
import com.lhy.pojo.Result;
import com.lhy.pojo.Student;
import com.lhy.pojo.StudentQueryParam;
import com.lhy.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class Studentcontroller {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public Result quertstudent(StudentQueryParam studentQueryParam)
    {
        log.info("查询学生参数: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.queryStudent(studentQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping("/students")
    public Result addStudent(@RequestBody Student student)
    {
        studentService.addStudent(student);
        return Result.success();
    }
    @GetMapping("/students/{id}")
    public Result getStudentById(@PathVariable Integer id)
    {
        log.info("查询学生参数: {}", id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    @PutMapping("/students")
    public Result updateStudent(@RequestBody Student student)
    {
        log.info("更新学生参数: {}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    @DeleteMapping("/students/{ids}")
    public Result deleteStudent(@PathVariable List<Integer> ids)
    {
        log.info("删除学生参数: {}", ids);
        studentService.deleteStudent(ids);
        return Result.success();
    }
    @PutMapping("/students/violation/{id}/{score}")
    public Result updateStudentViolation(@PathVariable Integer id, @PathVariable Integer score)
    {
        log.info("更新学生违规参数: {}", id, score);
        studentService.updateStudentViolation(id, score);
        return Result.success();
    }
}
