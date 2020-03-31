package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.dto.HomeBlog;
import com.lrj.myblogmybatis.pojo.Tag;

import java.util.List;

public interface TagDao {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag tag);
    //参数为传入的tag

    Tag getByName(String name);

    List<Tag> getAllTags();
    //前端根据tag查询博客
    List<Tag> getAdminTag();

}