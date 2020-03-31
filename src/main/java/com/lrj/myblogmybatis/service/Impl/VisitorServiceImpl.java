package com.lrj.myblogmybatis.service.Impl;


import com.lrj.myblogmybatis.dao.VisitorDao;
import com.lrj.myblogmybatis.pojo.Visitor;
import com.lrj.myblogmybatis.service.MailService;
import com.lrj.myblogmybatis.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/7 19:50
 */

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorDao visitorDao;

    @Autowired
    private MailService mailService;


    @Override
    public void getCode(String email, String code) {
        String subject = "来自allpassaway博客的邮件";
        String content = "你邮箱注册的验证码：" + code ;
        mailService.sendHtmlMail(email, subject, content);
    }

    @Transactional
    @Override
    public int saveVisitor(Visitor visitor) {
        return visitorDao.saveVisitor(visitor);
    }

    @Override
    public Visitor getUserByUseremail(String email) {
        return visitorDao.getUserByUseremail(email);
    }

    @Override
    public String getAvatar(Long id) {
        return visitorDao.getAvatar(id);
    }
}
