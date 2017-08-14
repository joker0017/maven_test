package com.smart.service;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/conf/smart-*.xml")
public class UserServiceTests {

    private UserService userService;

    @Test
    public void matchUser(){
        boolean isTrue = userService.hasMarchUser("admin","123456");
        System.out.println(isTrue);
    }

    @Test
    public void insertUser(){
        User user = new User();
        user.setLastVisit(new Date());
        user.setPassword("123456");
        user.setUserName("zhangsan");
        user.setCredits(2);
        user.setLastIp("192.168.1.222");
        Number number = userService.saveUser(user);
        System.out.println(number);
    }



    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
