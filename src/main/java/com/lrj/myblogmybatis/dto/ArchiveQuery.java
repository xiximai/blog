package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/17 15:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveQuery {
    private Long id;

    private String title;
    private String flag;
    private Date createTime;
}
