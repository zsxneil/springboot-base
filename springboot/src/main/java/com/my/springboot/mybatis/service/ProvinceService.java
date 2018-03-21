package com.my.springboot.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.my.springboot.mybatis.base.Page;
import com.my.springboot.mybatis.model.Province;

import java.util.List;

public interface ProvinceService {
    int insert(Province province);
    Province select(String id);
    PageInfo<Province> getOnePageProvinces(Page page);
}
