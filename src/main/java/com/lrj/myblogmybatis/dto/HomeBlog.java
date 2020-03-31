package com.lrj.myblogmybatis.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/29 20:46
 */

//首页展示的blog
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeBlog {
    private Long id;
    private String title;
    private String firstPicture;
    private Long views;
    private Date updateTime;
    private String description;

    //type
    private String typeName;

    //user

    private String nickName;
    private String avatar;
}
