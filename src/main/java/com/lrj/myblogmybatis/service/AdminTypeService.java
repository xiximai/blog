package com.lrj.myblogmybatis.service;


import com.lrj.myblogmybatis.pojo.Type;

import java.util.List;

public interface AdminTypeService {
    List<Type> getTypes();

    List<Type> getAllTypes();

    Type getById(long id);

    void deleteById(long id);

    int SaveType(Type type);

    Type getByName(String name);

    int updateType(Type type);


}
