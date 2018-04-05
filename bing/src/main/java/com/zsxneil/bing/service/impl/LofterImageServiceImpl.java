package com.zsxneil.bing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsxneil.bing.mappers.LofterImageMapper;
import com.zsxneil.bing.model.LofterImage;
import com.zsxneil.bing.service.LofterImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LofterImageServiceImpl implements LofterImageService {

    @Autowired
    LofterImageMapper imageMapper;

    @Override
    public int insert(LofterImage record) {
        return imageMapper.insert(record);
    }

    @Override
    public PageInfo<HashMap<String, Object>> selectPageImagesWithRefer(int pageNo) {
        PageHelper.startPage(pageNo, 100);
        List<HashMap<String ,Object>> resultList = imageMapper.selectAllImagesWithRefer();
        PageInfo<HashMap<String, Object>> pageInfo = new PageInfo<>(resultList);
        return pageInfo;
    }
}
