package com.lrj.myblogmybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lrj.myblogmybatis.dto.HomeBlog;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.service.AdminBlogService;
import com.lrj.myblogmybatis.service.AdminTypeService;
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
 * @date ：2020/3/4 22:28
 */

@Controller
public class TypeShowController {
    @Autowired
    private AdminTypeService adminTypeService;

    @Autowired
    private AdminBlogService adminBlogService;

    @GetMapping("/types/{id}")
    public String TypeShow(@RequestParam(defaultValue = "1" ,value = "pageNum") Integer pageNum,
                           Model model,
                           @PathVariable Long id){
        List<Type> types = adminTypeService.getAllTypes();
        if(id == -1){
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        List<HomeBlog> blogs = adminBlogService.getByTypeId(id);
        PageHelper.startPage(pageNum, 20);
        PageInfo<HomeBlog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
