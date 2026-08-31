package com.lhy.service;

import com.lhy.pojo.Clazz;
import com.lhy.pojo.ClazzQueryParam;
import com.lhy.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult queryClazzs(ClazzQueryParam params);

    void addClazz(Clazz clazz);

    Clazz queryClazzById(Integer id);

    void putclazz(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> queryClazzList();
}
