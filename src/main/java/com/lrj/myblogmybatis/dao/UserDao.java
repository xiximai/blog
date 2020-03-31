package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.pojo.User;




public interface UserDao {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserNameAndPassword(String username);

    User getByEmail(String email);
}