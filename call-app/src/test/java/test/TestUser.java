package test;

import com.school.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class TestUser {
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void loginTest() {
        User user = new User();
        user.setName("1001");
        user.setPassword("pass");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("user.login", user);

        for (User u: userList) {
            System.out.println(u);
        }
        sqlSession.close();
    }
}
