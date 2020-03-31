package com.lrj.myblogmybatis.enums;


import com.lrj.myblogmybatis.pojo.User;

/**
 * 通用状态类
 */

public enum StatusCode {
    Success(0,"成功"),
    Fail(-1,"失败"),
    InvalidParams(201,"非法的参数!"),
    UserNotLogin(202,"用户没登录"),
    Ok(200,"验证码发送中..."),
    UnOk(305,"邮箱和验证码不一致"),
    UnError(306,"用户名格式错误"),
    PwError(307,"密码错了"),
    HaveExist(308,"该邮箱已经注册"),
    EmailError(309,"找不到该邮箱"),
    UserNotfound(310,"用户不存在"),
    timeout(000,"验证码超时"),

    ;

    private Integer code;
    private String msg;

    StatusCode() {
    }

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }}


