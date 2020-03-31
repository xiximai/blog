package com.lrj.myblogmybatis.config;

import com.lrj.myblogmybatis.pojo.User;
import com.lrj.myblogmybatis.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 20:57
 */


public class UserRealm extends AuthorizingRealm {
    //用于用户查询
    @Autowired
    private UserService userService;
    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名
        User user=userService.checkUser(name);
        System.out.println("进行了授权");
        return null;
    }


    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //这一步是为了在POST请求的时候会先进行认证，然后再请求
        if(authenticationToken.getPrincipal()==null){
            return null;
        }
        //获取用户信息
        String name=authenticationToken.getPrincipal().toString();
        User user=userService.checkUser(name);
        if(user == null){
            //这里返回后会报出对应异常
            return null;
        }
        //如果用户存在
        //获取subject单例对象，把user放到session里面
        Subject subject=SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);

        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
