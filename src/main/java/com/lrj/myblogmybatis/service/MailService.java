package com.lrj.myblogmybatis.service;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/7 18:04
 * 发送包含验证码的邮件
 */


public interface MailService {

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject,String content);

    /**
     * 发送HTML邮件,方便用户注册登录评论点赞
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to , String subject, String content);
}
