package com.lrj.myblogmybatis.service;


import com.lrj.myblogmybatis.dto.*;
import com.lrj.myblogmybatis.pojo.Blog;

import java.util.List;

public interface AdminBlogService {
    List<BlogQuery> getAllBlogs();

    void DeleteById(long id);

    int SaveBlog(Blog blog);

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    List<BlogQuery> BlogSearch(BlogSearch blogSearch);

    List<HomeBlog> getHomeBlogs();

    //热度榜
    List<RecommendBlog> ShowRecommend();

    //查询博客详情
    DetailBlog getDetailBlogById(Long id);

    //根据条件查询博客
    List<HomeBlog> SearchBlogByQuery(String query);

    //更新博客 views
    void updateBlogView(Long id);

    //前端根据标签查询博客
    List<HomeBlog> getByTagId(Long id);

    //前端根据分类查询博客
    List<HomeBlog> getByTypeId(Long id);

    List<ArchiveQuery> getArchiveBlog(Integer year);

    int BlogCount();

    List<Integer> getYear();

    //持久化点赞
    int saveLike(LikeData likeData);

    int getLikeByUserIdAndBlogId(LikeData likeData);

    int deleteByUserIdAndBlogId(LikeData likeData);

}
