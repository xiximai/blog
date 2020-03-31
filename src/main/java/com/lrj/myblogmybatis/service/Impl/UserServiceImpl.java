package com.lrj.myblogmybatis.service.Impl;

import com.lrj.myblogmybatis.dao.UserDao;
import com.lrj.myblogmybatis.pojo.User;
import com.lrj.myblogmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 20:05
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username) {
        return userDao.selectByUserNameAndPassword(username);
    }
}
