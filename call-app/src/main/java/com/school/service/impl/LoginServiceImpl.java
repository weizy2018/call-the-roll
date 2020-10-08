package com.school.service.impl;

import com.school.entity.User;
import com.school.service.LoginService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    private static final String LOGIN = "user.login";

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public User loginCheck(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User u = sqlSession.selectOne(LOGIN, user);
        sqlSession.close();
        return u;
    }
}
