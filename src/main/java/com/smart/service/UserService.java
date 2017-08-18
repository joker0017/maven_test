package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    public boolean hasMarchUser(String userName,String password){
        int matchCount = userDao.getMatchCount(userName,password);
        if(matchCount>0){
            return true;
        }
        return false;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());
        this.userDao.updateLoginInfo(user);
        this.loginLogDao.insertLoginLog(loginLog);
    }
    @Transactional
    public Number saveUser(User user){
        Number number = userDao.insertUser(user);
        return number;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
