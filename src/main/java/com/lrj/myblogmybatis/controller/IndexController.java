package com.lrj.myblogmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/20 15:23
 */

@Controller
public class IndexController {
    @GetMapping("/")
    public String Index(){
        return "admin/login";
    }
}
