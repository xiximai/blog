package com.lrj.myblogmybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.common.LikeUtils;
import com.lrj.myblogmybatis.dto.*;
import com.lrj.myblogmybatis.enums.BType;
import com.lrj.myblogmybatis.pojo.Comment;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.service.AdminBlogService;
import com.lrj.myblogmybatis.service.AdminTagService;
import com.lrj.myblogmybatis.service.AdminTypeService;
import com.lrj.myblogmybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/28 19:51
 */
/*首页展示博客*/


@Controller
public class BlogShowController {
    @Autowired
    private AdminBlogService adminBlogService;

    @Autowired
    private AdminTagService adminTagService;

    @Autowired
    private AdminTypeService adminTypeService;

    @Autowired
    private CommentService commentService;

    /**
     * 统一标签，类别展示
     * */
    public void ListTagAndType(Model model){
        List<Tag> tags = adminTagService.getAllTag();//admin
        List<Type> types = adminTypeService.getAllTypes();//admin
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
    }


    /**
     * 首页
     * */
    @GetMapping("/blog")
    public String ShowBlog(@RequestParam(defaultValue = "1",value ="pageNum" )Integer pageNum,
                           Model model){
        PageHelper.startPage(pageNum, 6);
        List<HomeBlog> homeBlogs = adminBlogService.getHomeBlogs();
        List<RecommendBlog> RecommendBlogs = adminBlogService.ShowRecommend();
        PageInfo<HomeBlog> pageInfo = new PageInfo<>(homeBlogs);
        model.addAttribute("pageInfo", pageInfo);
        ListTagAndType(model);
        model.addAttribute("recommendBlogs",RecommendBlogs);
        return "index";
    }

    /**
     * 搜索
     */
    @GetMapping("/blog/search")
    public String SearchBlog(@RequestParam(defaultValue = "1",value ="pageNum" )Integer pageNum,
                             @RequestParam String query,Model model){
        List<HomeBlog> blogs = adminBlogService.SearchBlogByQuery(query);
        List<RecommendBlog> RecommendBlogs = adminBlogService.ShowRecommend();
        PageHelper.startPage(pageNum, 5);
        PageInfo<HomeBlog> pageInfo = new PageInfo<>(blogs);
        ListTagAndType(model);
        model.addAttribute("recommendBlogs",RecommendBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    /**
     * 观看增加
     * @param id
     */
    void ViewsAdd(Long id){
        adminBlogService.updateBlogView(id);
    }

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * 跳转到详情页
     */
    @GetMapping("/blog/{id}/show")
    public String BlogDetail(@PathVariable Long id , Model model){
        DetailBlog blog = adminBlogService.getDetailBlogById(id);
        List<Comment> comments = commentService.getCommentsByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        ViewsAdd(id);
        Long size = redisTemplate.opsForHash().size(LikeUtils.getLikeKey(BType.LIKED_ARTICLE, id));
        LikeData likeData = new LikeData();
        likeData.setLikeTotal(size);
        model.addAttribute("like", likeData);
        return "blog" ;
    }



}
