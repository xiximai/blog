package com.lrj.myblogmybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.dto.HomeBlog;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.service.AdminBlogService;
import com.lrj.myblogmybatis.service.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/4 18:29
 */

@Controller
public class TagShowController {
    @Autowired
    private AdminTagService adminTagService;

    @Autowired
    private AdminBlogService adminBlogService;

    @GetMapping("/tags/{id}")
    public String TagShow(@RequestParam (defaultValue = "1" ,value = "pageNum") Integer pageNum,
                          Model model, @PathVariable Long id){
        List<Tag> tags = adminTagService.getAllTag();
        model.addAttribute("tags", tags);
        if(id == -1){
            id = tags.get(0).getId();
        }
        List<HomeBlog> blogs = adminBlogService.getByTagId(id);
        PageHelper.startPage(pageNum, 20);
        PageInfo<HomeBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
