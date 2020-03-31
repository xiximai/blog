package com.lrj.myblogmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {

    private Long tagId;

    private Long blogId;

}