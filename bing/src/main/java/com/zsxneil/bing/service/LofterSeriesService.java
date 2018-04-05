package com.zsxneil.bing.service;

import com.github.pagehelper.PageInfo;
import com.zsxneil.bing.model.LofterSeries;

public interface LofterSeriesService {

    int insert(LofterSeries record);

    int insertSelective(LofterSeries record);

    PageInfo<LofterSeries> selectOnePage(int pageNo);

    boolean existLink(String seriesLink);
}
