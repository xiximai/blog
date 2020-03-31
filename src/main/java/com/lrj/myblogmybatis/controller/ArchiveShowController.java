package com.lrj.myblogmybatis.controller;

import com.lrj.myblogmybatis.dto.ArchiveQuery;
import com.lrj.myblogmybatis.service.AdminBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/17 15:37
 */


@Controller
public class ArchiveShowController {

    @Autowired
    private AdminBlogService adminBlogService;

    public Map<Integer,List<ArchiveQuery>> archiveShow(){
        List<Integer> years = adminBlogService.getYear();
        Map<Integer,List<ArchiveQuery>> map = new HashMap<>();
        for (Integer year : years) {
            map.put(year, adminBlogService.getArchiveBlog(year));
            System.out.println(map.toString());
        }
        return map;
    }



    @GetMapping("/archives")
    public String ArchiveShow(Model model){
        Map<Integer, List<ArchiveQuery>> map = archiveShow();
        int blogCount = adminBlogService.BlogCount();
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("map", map);
        return "archives";
    }
}
