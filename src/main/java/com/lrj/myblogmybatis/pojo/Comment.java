package com.lrj.myblogmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;

    private String nickname;

    private String email;

    private String content;

    private String avatar;

    private Date createTime;

    private Long blogId;


    private long visitorId;

    //回复评论
    private List<Ccomment> replyComments = new ArrayList<>();

    private Long parentCommentId;

    private boolean adminComment;


}