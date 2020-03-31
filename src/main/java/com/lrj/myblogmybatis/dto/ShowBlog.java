package com.lrj.myblogmybatis.dto;


import com.lrj.myblogmybatis.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/24 20:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowBlog {
    private Long id;
    private boolean published;
    private String flag;
    private String title;
    private String content;
    private Long typeId;
    private String tagIds;
    private String firstPicture;
    private String description;
    private boolean recommend;
    private boolean shareStatement;
    private boolean appreciation;
    private boolean commentabled;
    private Date updateTime;
}
