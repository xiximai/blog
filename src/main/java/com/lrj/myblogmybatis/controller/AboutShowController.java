package com.lrj.myblogmybatis.controller;

import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.service.AdminTagService;
import com.lrj.myblogmybatis.service.AdminTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/4 23:02
 */

@Controller
public class AboutShowController {
    @Autowired
    private AdminTypeService adminTypeService;

    @Autowired
    private AdminTagService adminTagService;

    @GetMapping("/about")
    public String AboutShow(Model model){
        List<Tag> tags = adminTagService.getAllTag();
        List<Type> types = adminTypeService.getAllTypes();
        model.addAttribute("tags", tags);
        model.addAttribute("types", types);
        return "about";
    }
}
