package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.pojo.Ccomment;
import com.lrj.myblogmybatis.pojo.Comment;

import java.util.List;

public interface CommentDao {
    int deleteComment(Long id);

    int saveComment(Comment comment);

    int insertSelective(Comment record);

    Comment findById(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //查询博客所属的全部评论
    List<Comment> getCommentsByBlogId (Long id);

    //查询子评论
    List<Ccomment> getChildComment(Long id);

    //保存子评论
    int saveCcomment (Ccomment ccomment);
}