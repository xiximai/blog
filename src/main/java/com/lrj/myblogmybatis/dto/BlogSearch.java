package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/26 18:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogSearch {
    private String title;

    private Long typeId;
    //前端传过来的recommend是string 格式 0，1
    private boolean recommend;


}
