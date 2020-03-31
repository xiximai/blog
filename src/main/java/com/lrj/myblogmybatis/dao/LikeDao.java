package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.dto.LikeData;

public interface LikeDao {
    int saveLike(LikeData likeData);

    int getLikeByUserIdAndBlogId(LikeData likeData);

    int deleteByUserIdAndBlogId(LikeData likeData);
}
