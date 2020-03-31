package com.lrj.myblogmybatis.service.Impl;

import com.lrj.myblogmybatis.dao.CommentDao;
import com.lrj.myblogmybatis.dao.UserDao;
import com.lrj.myblogmybatis.pojo.Ccomment;
import com.lrj.myblogmybatis.pojo.Comment;
import com.lrj.myblogmybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/14 21:13
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> getCommentsByBlogId(Long id) {
        List<Comment> commentsByBlogId = commentDao.getCommentsByBlogId(id);
        for(Comment comment : commentsByBlogId){
            Long parentId = comment.getId();
            List<Ccomment> childComments = getChildComment(parentId);
            if (childComments.size()>0){
                comment.setReplyComments(childComments);
            }
        }
        return commentsByBlogId;
    }

    @Autowired
    private UserDao userDao;

    //增加评论
    @Transactional
    @Override
    public int saveComment(Comment comment) {
        if (userDao.getByEmail(comment.getEmail())!=null){
            comment.setAdminComment(true);
        }
        else {
            comment.setAdminComment(false);
        }
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }

    //显示评论
    @Override
    public List<Ccomment> getChildComment(Long id) {
        return commentDao.getChildComment(id);
    }

    @Transactional
    @Override
    public int saveCcomment(Ccomment ccomment) {
        if (userDao.getByEmail(ccomment.getEmail())!=null){
            ccomment.setAdminComment(true);
        }
        ccomment.setCreateTime(new Date());
        return commentDao.saveCcomment(ccomment);
    }
}
