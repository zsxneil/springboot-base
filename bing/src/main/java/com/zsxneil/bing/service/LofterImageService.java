package com.zsxneil.bing.service;

import com.github.pagehelper.PageInfo;
import com.zsxneil.bing.model.LofterImage;

import java.util.HashMap;

public interface LofterImageService {

    int insert(LofterImage record);

    PageInfo<HashMap<String, Object>> selectPageImagesWithRefer(int pageNo);

}
