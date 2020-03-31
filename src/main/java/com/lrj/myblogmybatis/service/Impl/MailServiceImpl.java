package com.lrj.myblogmybatis.service.Impl;

import com.lrj.myblogmybatis.enums.StatusCode;
import com.lrj.myblogmybatis.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/7 18:11
 */

@Service
public class MailServiceImpl implements MailService {
    //日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    /**
     * 发送简单文本
     * @param to 收件者
     * @param subject 主题
     * @param content 文本内容
     */
    @Async
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
       try {
           SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
           simpleMailMessage.setFrom(env.getProperty("spring.mail.username"));
           simpleMailMessage.setTo(to);
           simpleMailMessage.setSubject(subject);
           simpleMailMessage.setText(content);
           mailSender.send(simpleMailMessage);

           logger.info("发送简单文本文件-发送成功");
       }catch (Exception e){
           logger.error("发送简单文本文件-发生异常", e.fillInStackTrace());
       }
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Async
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setFrom(env.getProperty("spring.mail.username"));
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(content, true);
            mailSender.send(mimeMessage);

            logger.info("邮件已经发送");
        }catch (MessagingException e){
            logger.error("邮件发送出现异常",e);
        }
    }
}
