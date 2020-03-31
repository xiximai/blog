package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.dto.*;
import com.lrj.myblogmybatis.pojo.Blog;

import java.util.List;

public interface BlogDao {
    int deleteByPrimaryKey(Long id);

    int insertBlog(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);
    //新增
    List<BlogQuery> getAllBlogs();

   //更新博客

    ShowBlog getBlogById(Long id);

    int updateBlog(ShowBlog showBlog);

    List<BlogQuery> searchByTitleOrTypeOrRecommend(BlogSearch blogSearch);

    /*首页博客*/
    List<HomeBlog> getHomeBlog();

    /**
     * 热度榜
     * */
    List<RecommendBlog> ShowRecommend();

    /**
     * 查询博客详情
     */
    DetailBlog ShowBlogById(Long id);
    /**
     * 根据条件搜索 模糊查询
     */
    List<HomeBlog> SearchBlogs(String query);

    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    Blog getById(Long id);

    List<HomeBlog> getByTagId(Long id);

    List<HomeBlog> getByTypeId(Long id);

    List<ArchiveQuery> getArchiveBlog(Integer year);

    int BlogCount();

    List<Integer> getYear();
}