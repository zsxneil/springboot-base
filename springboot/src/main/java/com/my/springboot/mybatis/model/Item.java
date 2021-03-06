package com.my.springboot.mybatis.model;

import java.util.Date;

public class Item {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.itemsname
     *
     * @mbggenerated
     */
    private String itemsname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.price
     *
     * @mbggenerated
     */
    private Float price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.pic
     *
     * @mbggenerated
     */
    private String pic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.createtime
     *
     * @mbggenerated
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column items.detail
     *
     * @mbggenerated
     */
    private String detail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.id
     *
     * @return the value of items.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.id
     *
     * @param id the value for items.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.itemsname
     *
     * @return the value of items.itemsname
     *
     * @mbggenerated
     */
    public String getItemsname() {
        return itemsname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.itemsname
     *
     * @param itemsname the value for items.itemsname
     *
     * @mbggenerated
     */
    public void setItemsname(String itemsname) {
        this.itemsname = itemsname == null ? null : itemsname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.price
     *
     * @return the value of items.price
     *
     * @mbggenerated
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.price
     *
     * @param price the value for items.price
     *
     * @mbggenerated
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.pic
     *
     * @return the value of items.pic
     *
     * @mbggenerated
     */
    public String getPic() {
        return pic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.pic
     *
     * @param pic the value for items.pic
     *
     * @mbggenerated
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.createtime
     *
     * @return the value of items.createtime
     *
     * @mbggenerated
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.createtime
     *
     * @param createtime the value for items.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column items.detail
     *
     * @return the value of items.detail
     *
     * @mbggenerated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column items.detail
     *
     * @param detail the value for items.detail
     *
     * @mbggenerated
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}