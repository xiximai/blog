package com.lrj.myblogmybatis.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 16:50
 */
//全局异常处理
@ControllerAdvice
public class ControllerHandler {
    //日志
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    /*//拦截所有的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView ExceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //日志记录错误信息
        logger.error("requestUrl: {} , exception: {} ", request,e.getMessage());

        //找不到资源的异常交给springboot处理,controlleradvice不进行拦截

        if(AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null){
            throw e;
        }
        //返回数据
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("url", request.getRequestURI());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }*/

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return jsonObject;
    }

}
