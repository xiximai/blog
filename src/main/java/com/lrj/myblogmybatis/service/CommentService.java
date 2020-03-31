package com.lrj.myblogmybatis.service;

import com.lrj.myblogmybatis.pojo.Ccomment;
import com.lrj.myblogmybatis.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBlogId(Long id);

    int saveComment(Comment comment);

    //查询子平陵
    List<Ccomment> getChildComment(Long id);

    int saveCcomment(Ccomment ccomment);


}
