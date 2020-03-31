package com.lrj.myblogmybatis.controller;

import com.lrj.myblogmybatis.pojo.Comment;
import com.lrj.myblogmybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.jws.WebParam;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/14 20:57
 */

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;


    //评论之后刷新
    @PostMapping("/comments")
    public String getComment(Comment comment,Model model){
        List<Comment> commentsByBlogId = commentService.getCommentsByBlogId(comment.getId());
        model.addAttribute("comments", commentsByBlogId);
        return "blog :: commentList";
    }
}
