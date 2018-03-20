package com.my.springboot.mybatis.service;

import com.my.springboot.mybatis.model.Item;

public interface ItemService {
    int insert(Item item);
    int insertSelective(Item item);
    int updateByPrimaryKey(Item item);
    int updateByPrimaryKeySelective(Item item);
    int update(Item item);
    int delete(Integer id);
    Item select(Integer id);
}
