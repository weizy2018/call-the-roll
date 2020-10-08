package com.school.service.impl;

import com.school.entity.Grade;
import com.school.service.GradeService;
import com.school.util.ReturnCode;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {
    private static final Logger logger = LoggerFactory.getLogger(GradeServiceImpl.class);

    private static final String GET_GRADE_BY_USER_ID = "grade.getGradeByUserId";
    private static final String ADD_GRADE = "grade.addGrade";
    private static final String ADD_USER_GRADE = "userGrade.addUserGrade";

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Grade> getGradeByUserId(Integer userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Grade> gradeList = sqlSession.selectList(GET_GRADE_BY_USER_ID, userId);
        sqlSession.close();
        return gradeList;
    }

    @Override
    public Grade addGrade(Integer userId, String gradeName) {
        Grade grade = new Grade();
        grade.setName(gradeName);
        grade.setCreateTime(new Date());


        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            sqlSession.insert(ADD_GRADE, grade);

            Map<String, Integer> map = new HashMap<>(2);
            map.put("userId", userId);
            map.put("gradeId", grade.getId());

            sqlSession.insert(ADD_USER_GRADE, map);
            sqlSession.commit();
            return grade;

        } catch (Exception e) {
            logger.error("添加班级失败: {}", String.valueOf(e));
            sqlSession.rollback();
            return null;

        } finally {
            sqlSession.close();
        }

    }
}
