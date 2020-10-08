package com.school.service;

import com.school.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents(Integer gradeId);

    Student addStudent(Integer gradeId, Student student);

    int updateStudent(Student student);

    int deleteStudent(Student student);

}
