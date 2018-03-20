package com.my.springboot.mybatis.controller;

import com.my.springboot.mybatis.model.Item;
import com.my.springboot.mybatis.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ItemController
{
    @Autowired
    ItemService itemService;

    @ResponseBody
    @RequestMapping("/mybatis/item/{id}")
    public Item findItem(@PathVariable("id") Integer id) {
        return itemService.select(id);
    }

    @ResponseBody
    @RequestMapping("/mybatis/item/insert")
    public Item insertItem() {
        Item item = new Item();
        item.setCreatetime(new Date());
        item.setDetail("mybtais test");
        item.setPic("path");
        item.setPrice(3330.5f);
        item.setItemsname("mybatis");
        itemService.insert(item);
        return item;
    }
}
