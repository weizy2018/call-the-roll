package com.school.service.impl;

import com.school.entity.Student;
import com.school.service.StudentService;
import com.school.util.ReturnCode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private static final String STUDENT_GET_STUDENTS        = "student.getStudents";
    private static final String STUDENT_ADD_STUDENT         = "student.addStudent";
    private static final String STUDENT_UPDATE_STUDENT      = "student.updateStudent";
    private static final String STUDENT_DELETE_STUDENT      = "student.deleteStudent";

    private static final String STUDENT_GRADE_ADD_STUDENT   = "studentGrade.addStudent";
    private static final String STUDENT_GRADE_DELETE_STUDENT= "studentGrade.deleteStudentGrade";

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Student> getStudents(Integer gradeId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> studentList = sqlSession.selectList(STUDENT_GET_STUDENTS, gradeId);
        sqlSession.close();
        return studentList;
    }

    @Override
    public Student addStudent(Integer gradeId, Student student) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert(STUDENT_ADD_STUDENT, student);

            Map<String, Integer> map = new HashMap<>(2);
            map.put("studentId", student.getId());
            map.put("gradeId", gradeId);

            sqlSession.insert(STUDENT_GRADE_ADD_STUDENT, map);

            sqlSession.commit();
            return student;

        } catch (Exception e) {
            logger.error("学生添加失败 {}", String.valueOf(e));
            sqlSession.rollback();
            return null;

        } finally {
            sqlSession.close();
        }

    }

    @Override
    public int updateStudent(Student student) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            int ret = sqlSession.update(STUDENT_UPDATE_STUDENT, student);
            sqlSession.commit();
            return ret;

        } catch (Exception e) {
            logger.error("更新失败: {}", String.valueOf(e));
            return 0;

        } finally {
            sqlSession.close();
        }

    }

    @Override
    public int deleteStudent(Student student) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.delete(STUDENT_DELETE_STUDENT, student.getId());
            sqlSession.delete(STUDENT_GRADE_DELETE_STUDENT, student.getId());

            return ReturnCode.SUCCESS;

        } catch (Exception e) {
            sqlSession.rollback();
            return ReturnCode.FAIL;

        } finally {
            sqlSession.close();
        }
    }

}
