package com.school.controller;

import com.school.entity.User;
import com.school.service.LoginService;
import com.school.util.ReturnCode;
import com.school.util.ReturnData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login")
    public ReturnData login(@RequestBody User user) {
        logger.info("user: {}", user);

        User u = loginService.loginCheck(user);
        if (u == null) {
            return new ReturnData(ReturnCode.FAIL, "账号货密码错误", null);
        }
        return new ReturnData(ReturnCode.SUCCESS, "登陆成功", u);
    }
}
