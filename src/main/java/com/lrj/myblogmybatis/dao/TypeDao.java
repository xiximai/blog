package com.lrj.myblogmybatis.dao;

import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Type;

import java.util.List;

public interface TypeDao {
    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    Type getByName(String name);

    List<Type> getAllTypes();

    List<Type> getAdminType();
}