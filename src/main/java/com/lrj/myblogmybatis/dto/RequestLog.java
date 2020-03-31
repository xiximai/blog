package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 18:40
 */
//用来封装得到的请求信息方便日志记录
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;
}
