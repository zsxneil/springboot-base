package com.my.springboot.mybatis.base;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Page {

    private Integer page = 1;

    private Integer rows = 10;

    private String orderBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", rows=" + rows +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
