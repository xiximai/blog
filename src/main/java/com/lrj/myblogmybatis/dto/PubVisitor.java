package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/14 18:24
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubVisitor {
    private Long id;
    private String username;
    private String useremail;
    private String content;
    private Long blogId;
}
