package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.pojo.Visitor;

public interface VisitorDao {

    int saveVisitor(Visitor visitor);

    Visitor getUserByUseremail(String email);

    Visitor getUserByUserId(Long id);

    String getAvatar(Long id);
}
