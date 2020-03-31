package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/15 22:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyComment {
    private Long id;
    private String content;
    private String useremail;
    private String username;
    private Long blogId;
    private String commentnickname;
    private Long parentid;
    private Long commentid;
}
