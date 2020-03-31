package com.lrj.myblogmybatis.service.Impl;

import com.lrj.myblogmybatis.dao.TypeDao;
import com.lrj.myblogmybatis.pojo.Tag;
import com.lrj.myblogmybatis.pojo.Type;
import com.lrj.myblogmybatis.service.AdminTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/2/22 21:13
 */

@Service
public class AdminTypeServiceImpl implements AdminTypeService {
    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public List<Type> getTypes() {
        return typeDao.getAdminType();
    }
    @Transactional
    @Override
    public Type getById(long id) {
        return typeDao.selectByPrimaryKey(id);
    }
    @Transactional
    @Override
    public List<Type> getAllTypes() {
        return typeDao.getAllTypes();
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        typeDao.deleteByPrimaryKey(id);
    }
    @Transactional
    @Override
    public int SaveType(Type type) {
        return typeDao.insert(type);
    }
    @Transactional
    @Override
    public Type getByName(String name) {
        return typeDao.getByName(name);
    }
    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateByPrimaryKey(type);
    }




}
