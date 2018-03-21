package com.my.springboot.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.springboot.mybatis.base.Page;
import com.my.springboot.mybatis.mappers.ProvinceMapper;
import com.my.springboot.mybatis.model.Province;
import com.my.springboot.mybatis.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 分页测试
     * @param page
     * @return
     */
    @Override
    public PageInfo<Province> getOnePageProvinces(Page page) {
        page.setOrderBy("code desc");
        System.out.println(page);
        PageHelper.startPage(page.getPage(), page.getRows(), "code desc");
        //PageHelper.startPage(page); //直接使用这种方式，报空指针，未找到原因
        List<Province> provinceList = provinceMapper.getAllProvinces();
        PageInfo<Province> pageInfo = new PageInfo<>(provinceList);
        return pageInfo;
    }
}
