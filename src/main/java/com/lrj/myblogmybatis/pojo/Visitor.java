package com.lrj.myblogmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/7 16:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    private Long id;

    @Email(message = "邮箱号不正确")
    private String email;

    private String password;

    private String nickname;
    private String avatar;
    //标志 切换表单
    private boolean reg;
    //临时验证码
    private String code;
}
