package com.lhy.mapper;

import com.lhy.pojo.Clazz;
import com.lhy.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryParam params);

    void add(Clazz clazz);

    Clazz queryClazzById(Integer id);

    void putclazz(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> queryClazzList();
}
