package com.lrj.myblogmybatis.service;


import com.lrj.myblogmybatis.pojo.Visitor;

public interface VisitorService {

    void getCode(String email, String code);

    int saveVisitor(Visitor visitor);

    Visitor getUserByUseremail(String email);

    String getAvatar(Long id);
}
