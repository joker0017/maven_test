package com.smart.dao;

import com.smart.dao.UserDao;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import java.util.Date;

@ContextConfiguration("classpath*:/conf/smart-*.xml")
public class UserDaoTests {

    private UserDao userDao;

    @Test
    public void findUserByUserName(){
        User user = userDao.findUserByName("admin");
        System.out.println(user);
    }

    public void insertUser(){
        User user = new User();
        user.setCredits(1);
        user.setUserName("zhangsan");
        user.setPassword("123456");
        user.setLastVisit(new Date());
        userDao.updateLoginInfo(user);
    }

    public UserDao getUserDao() {
        return userDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
