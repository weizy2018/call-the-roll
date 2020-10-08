package com.school.controller;

import com.school.entity.Grade;
import com.school.service.GradeService;
import com.school.util.ReturnCode;
import com.school.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GradeController {
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/getGrades")
    public ReturnData getGrades(Integer userId) {
        List<Grade> gradeList = gradeService.getGradeByUserId(userId);

        return new ReturnData(ReturnCode.SUCCESS, "查询结果", gradeList);
    }

    @RequestMapping(value = "/addGrade")
    public ReturnData addGrade(@RequestParam Integer userId, @RequestParam String gradeName) {
        // TODO 返回Grade
        Grade grade = gradeService.addGrade(userId, gradeName);
        if (grade != null) {
            return new ReturnData(ReturnCode.SUCCESS, "添加成功", grade);
        } else {
            return new ReturnData(ReturnCode.FAIL, "添加失败", null);
        }
    }
}
