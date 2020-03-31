package com.lrj.myblogmybatis.dto;

import com.lrj.myblogmybatis.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/24 18:36
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;

    private Type type;
}
