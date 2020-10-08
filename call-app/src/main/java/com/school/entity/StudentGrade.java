package com.school.entity;

public class StudentGrade {
    private Integer id;
    private Integer studentId;
    private Integer gradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "StudentGrade{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", gradeId=" + gradeId +
                '}';
    }
}
