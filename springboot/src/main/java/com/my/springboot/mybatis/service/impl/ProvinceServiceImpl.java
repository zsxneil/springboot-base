package com.my.springboot.mybatis.service.impl;

import com.my.springboot.mybatis.mappers.ProvinceMapper;
import com.my.springboot.mybatis.model.Province;
import com.my.springboot.mybatis.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceMapper provinceMapper;

    @Override
    public int insert(Province province) {
        return provinceMapper.insert(province);
    }

    @Override
    public Province select(String id) {
        return provinceMapper.selectByPrimaryKey(id);
    }
}
