package com.lrj.myblogmybatis.controller.admin;


import com.lrj.myblogmybatis.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 15:15
 */

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String LoginPage(){return "admin/login";}

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String  password, RedirectAttributes attributes, HttpSession session){
        System.out.println("用户名："+username+"       密码:        "+password);
        //获取当前用户的单例对象
        Subject subject= SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "admin/success";
        }catch (UnknownAccountException e){ //用户名不存在
            attributes.addFlashAttribute("message", "用户名错误");
            return "redirect:/admin";
        }catch (IncorrectCredentialsException e){ //密码不正确
            attributes.addFlashAttribute("message", "密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/loginOut")
    public String LoginOut(){
        return "/admin/login";
    }

}
