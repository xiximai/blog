package com.lrj.myblogmybatis.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 20:57
 */

@Configuration
public class ShiroConfig {
    //将自己的验证方式加入容器
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //权限管理,配置主要是realm的管理认证
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        return manager;
    }
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        /*
         * anno:无需认证就可以访问
         * authc:必须认证才能访问
         * user:必须拥有记住我功能才能用
         * perms:拥有对某个资源的权限
         * role:拥有某个角色权限才能访问
         * */

        //
        Map<String,String> map=new HashMap<>();

        //授权
        map.put("/admin/**", "authc");

        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/admin");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
}
