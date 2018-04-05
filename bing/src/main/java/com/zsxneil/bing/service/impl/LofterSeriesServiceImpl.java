package com.zsxneil.bing.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsxneil.bing.mappers.LofterSeriesMapper;
import com.zsxneil.bing.model.LofterSeries;
import com.zsxneil.bing.service.LofterSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LofterSeriesServiceImpl implements LofterSeriesService {

    @Autowired
    LofterSeriesMapper seriesMapper;


    @Override
    public int insert(LofterSeries record) {
        return seriesMapper.insert(record);
    }

    @Override
    public int insertSelective(LofterSeries record) {
        return seriesMapper.insertSelective(record);
    }

    @Override
    public PageInfo<LofterSeries> selectOnePage(int pageNo) {
        PageHelper.startPage(pageNo, 100, "id asc");
        //PageHelper.startPage(page); //直接使用这种方式，报空指针，未找到原因
        List<LofterSeries> seriesList = seriesMapper.getAllSeries();
        PageInfo<LofterSeries> pageInfo = new PageInfo<>(seriesList);
        return pageInfo;
    }

    @Override
    public boolean existLink(String seriesLink) {
        LofterSeries series = seriesMapper.selectOneByLink(seriesLink);
        if (series != null) {
            return true;
        }
        return false;
    }
}
