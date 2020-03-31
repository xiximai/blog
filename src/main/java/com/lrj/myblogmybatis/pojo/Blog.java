package com.lrj.myblogmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Long id;

    private String title;

    private String content;

    private String firstPicture;

    private String flag;

    private Integer views;

    private boolean appreciation;

    private boolean shareStatement;

    private boolean commentabled;

    private boolean published;

    private boolean recommend;

    private Date createTime;

    private Date updateTime;

    private Long typeId;

    private Long userId;

    private String description;

    private String tagIds;

    private Type type;

    private User user;

    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

/*    //点赞---数量
    private Integer like;
    private Integer unlike;*/

    }