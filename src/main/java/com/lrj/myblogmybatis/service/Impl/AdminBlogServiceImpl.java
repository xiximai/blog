package com.lrj.myblogmybatis.service.Impl;

import com.lrj.myblogmybatis.common.MarkdownUtils;
import com.lrj.myblogmybatis.dao.BlogAndTagDao;
import com.lrj.myblogmybatis.dao.BlogDao;
import com.lrj.myblogmybatis.dao.LikeDao;
import com.lrj.myblogmybatis.dao.VisitorDao;
import com.lrj.myblogmybatis.dto.*;
import com.lrj.myblogmybatis.exception.NotFoundException;
import com.lrj.myblogmybatis.pojo.Blog;
import com.lrj.myblogmybatis.pojo.BlogAndTag;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Visitor;
import com.lrj.myblogmybatis.service.AdminBlogService;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/23 17:38
 */

@Service
public class AdminBlogServiceImpl  implements AdminBlogService {

    @Autowired
    private BlogDao blogDao;


    @Autowired
    private BlogAndTagDao blogAndTagDao;

    @Transactional
    @Override
    public List<BlogQuery> getAllBlogs() {
        return blogDao.getAllBlogs();
    }

    @Transactional
    @Override
    public void DeleteById(long id) {
        blogDao.deleteByPrimaryKey(id);
        blogAndTagDao.deleteById(id);
    }

    @Transactional
    @Override
    public int  SaveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
       /*//将标签的数据存到t_blogs_tag表中
        System.out.println("-----------------调试-----------------");
        System.out.println(blog.toString());
        System.out.println("-----------------截图后四位作为id--------");*/
        Long newId = LongToSmall(blog.getId());
        blog.setId(newId);
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
        blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
        blogAndTagDao.saveBlogAndTags(blogAndTag);
    }
        return blogDao.insertBlog(blog);
    }

    //时间戳作为id，太长  截取后四位
    public static Long  LongToSmall(Long id){
        String s = String.valueOf(id);
        System.out.println(s);
        s= StringUtils.substring(s, s.length()-4);
        Long newId = Long.valueOf(s);
        System.out.println(newId);
        return newId;
    }

    public static void main(String[] args) {
        Long newId =LongToSmall((long) 923312321);
        System.out.println(newId);
    }

    /*1.java中如何将string 转化成long

    long l = Long.parseLong([String]);

    或

    long l = Long.parseLong([String],[int radix]);

    long l = Long.valueOf("123").longValue();

    2.Long.ValueOf("String")与Long.parseLong("String")的区别

    Long.ValueOf("String")返回Long包装类型

    Long.parseLong("String")返回long基本数据类型*/

    @Transactional
    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Transactional
    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        blogAndTagDao.deleteById(showBlog.getId());
        String tagIds = showBlog.getTagIds();
        String[] split = tagIds.split(",");
        for (int i = 0; i<split.length; i++){
            blogAndTagDao.saveBlogAndTags(new BlogAndTag(Long.parseLong(split[i]), showBlog.getId()));
        }
        return blogDao.updateBlog(showBlog);
    }

    @Transactional
    @Override
    public List<BlogQuery> BlogSearch(BlogSearch blogSearch) {
        return blogDao.searchByTitleOrTypeOrRecommend(blogSearch);
    }

    @Transactional
    @Override
    public List<HomeBlog> getHomeBlogs() {
        return blogDao.getHomeBlog();
    }

    @Override
    public List<RecommendBlog> ShowRecommend() {
        return blogDao.ShowRecommend();
    }

    @Override
    public DetailBlog getDetailBlogById(Long id) {
        DetailBlog blog = blogDao.ShowBlogById(id);
        if (blog == null){
            throw  new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;

    }

    @Override
    public List<HomeBlog> SearchBlogByQuery(String query) {
        return blogDao.SearchBlogs(query);
    }

    //更新博客views
    @Transactional
    @Override
    public void updateBlogView(Long id) {
        Blog blog = blogDao.getById(id);
        blog.setViews(blog.getViews()+1); //访问一次就增加一次
        blogDao.updateByPrimaryKey(blog);
    }

    @Override
    public List<HomeBlog> getByTagId(Long id) {
        return blogDao.getByTagId(id);
    }

    @Override
    public List<HomeBlog> getByTypeId(Long id) {
        return blogDao.getByTypeId(id);
    }

    @Override
    public List<ArchiveQuery> getArchiveBlog(Integer year) {
        return blogDao.getArchiveBlog(year);
    }



    @Override
    public int BlogCount() {
        return blogDao.BlogCount();
    }

    @Override
    public List<Integer> getYear() {
        return blogDao.getYear();
    }

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private VisitorDao visitorDao;

    @Transactional
    @Override
    public int saveLike(LikeData likeData) {
        Visitor visitor = visitorDao.getUserByUserId(likeData.getUserId());
        likeData.setEmail(visitor.getEmail());
        likeData.setNickname(visitor.getNickname());
        return likeDao.saveLike(likeData);
    }

    @Override
    public int getLikeByUserIdAndBlogId(LikeData likeData) {
        return likeDao.getLikeByUserIdAndBlogId(likeData);
    }

    @Transactional
    @Override
    public int deleteByUserIdAndBlogId(LikeData likeData) {
        return likeDao.deleteByUserIdAndBlogId(likeData);
    }
}
