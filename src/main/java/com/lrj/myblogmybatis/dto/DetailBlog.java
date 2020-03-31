package com.lrj.myblogmybatis.dto;

import com.lrj.myblogmybatis.pojo.Comment;
import com.lrj.myblogmybatis.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/3 11:13
 * 博客详情页
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailBlog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private Integer views;
    private boolean appreciation;
    private String flag;
    private boolean commentabled;
    private Date updateTime;
    private String description;

/*    //type
    private String typeName;*/

    //user

    private String nickname;
    private String avatar;

    private String tagIds;

    private List<Tag> tags = new ArrayList<>();

}
