package com.lrj.myblogmybatis.service;

import com.lrj.myblogmybatis.dto.HomeBlog;
import com.lrj.myblogmybatis.pojo.Tag;

import java.util.List;

public interface AdminTagService {
    List<Tag> getTags();

    Tag getById(long id);

    void deleteById(long id);

    int SaveTag(Tag tag);

    Tag getByName(String name);

    int updateTag(Tag tag);

    List<Tag> getAllTag();

    List<Tag> getTagByString(String text);


}
