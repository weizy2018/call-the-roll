package com.school.controller;

import com.school.entity.Student;
import com.school.params.StudentRequestParams;
import com.school.service.StudentService;
import com.school.util.ReturnCode;
import com.school.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getStudents")
    public ReturnData getStudents(Integer gradeId) {
        List<Student> studentList = studentService.getStudents(gradeId);

        return new ReturnData(ReturnCode.SUCCESS, "", studentList);
    }

    @RequestMapping(value = "/addStudent")
    public ReturnData addStudent(@RequestBody StudentRequestParams params) {

        Student s = studentService.addStudent(params.getGradeId(), params.getStudent());
        if (s != null) {
            return new ReturnData(ReturnCode.SUCCESS, "添加成功", s);
        } else {
            return new ReturnData(ReturnCode.FAIL, "添加失败", null);
        }

    }

    @RequestMapping(value = "/updateStudent")
    public ReturnData updateStudent(@RequestBody Student student) {
        int ret = studentService.updateStudent(student);
        if (ret != 0) {
            return new ReturnData(ReturnCode.SUCCESS, "更新成功", student);
        } else {
            return new ReturnData(ReturnCode.FAIL, "更新失败", null);
        }
    }

    @RequestMapping(value = "/deleteStudent")
    public ReturnData deleteStudent(@RequestBody Student student) {
        int ret = studentService.deleteStudent(student);

        if (ret != 0) {
            return new ReturnData(ReturnCode.SUCCESS, "删除成功", null);
        } else {
            return new ReturnData(ReturnCode.FAIL, "删除失败", null);
        }
    }
}
