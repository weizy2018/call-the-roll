package com.school.service;

import com.school.entity.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> getGradeByUserId(Integer userId);

    Grade addGrade(Integer userId, String gradeName);
}
