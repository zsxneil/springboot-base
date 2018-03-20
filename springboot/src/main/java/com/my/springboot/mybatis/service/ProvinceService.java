package com.my.springboot.mybatis.service;

import com.my.springboot.mybatis.model.Province;

public interface ProvinceService {
    int insert(Province province);
    Province select(String id);
}
