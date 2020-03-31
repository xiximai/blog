package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/2 22:04
 */

/*
* 热榜单--前五
* */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {
    private Long id;
    private String title;

    private boolean recommend;
}
