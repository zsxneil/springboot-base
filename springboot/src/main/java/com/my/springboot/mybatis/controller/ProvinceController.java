package com.my.springboot.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.my.springboot.mybatis.base.Page;
import com.my.springboot.mybatis.model.Item;
import com.my.springboot.mybatis.model.Province;
import com.my.springboot.mybatis.service.ItemService;
import com.my.springboot.mybatis.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class ProvinceController
{
    @Autowired
    ProvinceService provinceService;

    @ResponseBody
    @RequestMapping("/mybatis/province/{id}")
    public Province findProvince(@PathVariable("id") String id) {
        return provinceService.select(id);
    }

    @ResponseBody
    @RequestMapping("/mybatis/province/insert")
    public Province insertProvince() {
        Province province = new Province();
        province.setCode(895);
        province.setName("广东");
        provinceService.insert(province);
        return province;
    }

    /**
     * 分页测试
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/mybatis/province/list")
    public PageInfo<Province> list(Integer page){
        Page pageInfo = new Page();
        if (page == null)
            page = 1;
        pageInfo.setPage(page);
        pageInfo.setRows(3);
        return provinceService.getOnePageProvinces(pageInfo);
    }
}
