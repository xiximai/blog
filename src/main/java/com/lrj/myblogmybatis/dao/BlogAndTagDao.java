package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.pojo.BlogAndTag;

public interface BlogAndTagDao {
    int saveBlogAndTags(BlogAndTag blogAndTag);
    void deleteById(Long id);

}