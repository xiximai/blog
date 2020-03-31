package com.lrj.myblogmybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/22 13:42
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LikeData {
    @NotNull
    private Long blogId;
    @NotNull
    private Long userId;


    private String nickname;

    private String email;

    private Long likeTotal;

    private Boolean isLike;

}
