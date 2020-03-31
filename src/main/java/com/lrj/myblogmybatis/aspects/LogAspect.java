package com.lrj.myblogmybatis.aspects;

import com.lrj.myblogmybatis.dto.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 18:14
 */
//被springBoot托管
@Component
//aop切面-----执行方法过程中日志的处理
@Aspect
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    //这个注解代表这是一个切面,execution()表示需要拦截
    //controller下的所有类和方法
    @Pointcut("execution(* com.lrj.myblogmybatis.controller.*.*(..))")
    public void Log(){

    }

    /**
     * @author: xiximai
     * @description: TODO
     * @date: 2020/2/20 18:29
     * @param joinPoint  封装了代理方法信息的对象,若用不到则可以忽略不写
     * @return void
     */
    @Before("Log()")
    public void doBefore(JoinPoint joinPoint){
        //attributes 可以获得requetst的信息
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获得请求的request
        HttpServletRequest request = attributes.getRequest();
        //获取url
        String url = request.getRequestURL().toString();
        //获取ip地址
        String ip = request.getRemoteAddr();
        //获取封装方法
        /*
        目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        目标方法名为:" + joinPoint.getSignature().getName());
        * */
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //获取传入方法的参数
        Object[] args = joinPoint.getArgs();
        //封装到dto中
        RequestLog requestLog=new RequestLog(url, ip, classMethod, args);
        logger.info("request: {}", requestLog);

    }
    @After("Log()")
    public void doAfter(){
        logger.info("--------------------请求信息输出完成------------");
    }
    //捕获返回的内容
    @AfterReturning(returning = "result",pointcut = "Log()")
    public void afterReturn(Object result){
        logger.info("result: {}",result);

    }

}
