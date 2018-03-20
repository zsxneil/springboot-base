package com.my.springboot.mybatis.service.impl;

import com.my.springboot.mybatis.mappers.ItemMapper;
import com.my.springboot.mybatis.model.Item;
import com.my.springboot.mybatis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public int insert(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public int insertSelective(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public int updateByPrimaryKey(Item item) {
        return itemMapper.updateByPrimaryKey(item);
    }

    @Override
    public int updateByPrimaryKeySelective(Item item) {
        return itemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public int update(Item item) {
        return itemMapper.updateByPrimaryKeyWithBLOBs(item);
    }

    @Override
    public int delete(Integer id) {
        return itemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Item select(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }


}
