package com.school.params;

import com.school.entity.Student;

public class StudentRequestParams {
    private Integer gradeId;
    private Student student;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentRequestParams{" +
                "grade=" + gradeId +
                ", student=" + student +
                '}';
    }
}
