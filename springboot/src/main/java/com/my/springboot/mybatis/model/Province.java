package com.my.springboot.mybatis.model;

public class Province {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column province.code
     *
     * @mbggenerated
     */
    private Integer code;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.id
     *
     * @return the value of province.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.id
     *
     * @param id the value for province.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.name
     *
     * @return the value of province.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.name
     *
     * @param name the value for province.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column province.code
     *
     * @return the value of province.code
     *
     * @mbggenerated
     */
    public Integer getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column province.code
     *
     * @param code the value for province.code
     *
     * @mbggenerated
     */
    public void setCode(Integer code) {
        this.code = code;
    }
}