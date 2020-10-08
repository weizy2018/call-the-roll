package com.school.entity;

public class UserGrade {
    private Integer id;
    private Integer userId;
    private Integer gradeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "UserGrade{" +
                "id=" + id +
                ", userId=" + userId +
                ", gradeId=" + gradeId +
                '}';
    }
}
