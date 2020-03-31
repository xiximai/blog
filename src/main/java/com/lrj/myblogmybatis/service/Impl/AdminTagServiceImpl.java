package com.lrj.myblogmybatis.service.Impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lrj.myblogmybatis.dao.TagDao;
import com.lrj.myblogmybatis.pojo.BlogAndTag;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.service.AdminTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/21 23:59
 */

@Service
public class AdminTagServiceImpl implements AdminTagService {
    @Autowired
    private TagDao tagDao;



    @Transactional
    @Override
    public List<Tag> getTags() {
        return tagDao.getAdminTag();
    }

    @Transactional
    @Override
    public Tag getById(long id) {
        return tagDao.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        tagDao.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int SaveTag(Tag tag) {
        return tagDao.insert(tag);
    }

    @Transactional
    @Override
    public Tag getByName(String name) {
        return tagDao.getByName(name);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag ){
        return tagDao.updateByPrimaryKey(tag);
    }

    @Transactional
    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTags();
    }

    @Transactional
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for(Long along : longs){
            tags.add(tagDao.selectByPrimaryKey(along));
        }
        return tags;
    }

    //接收页面传来的tag.id集合
    //格式为字符串形式，转换成整型
    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] split = ids.split(",");
            for (int i=0; i<split.length; i++){
                list.add(new Long(split[i]));
            }
        }
        return list;
    }


}
