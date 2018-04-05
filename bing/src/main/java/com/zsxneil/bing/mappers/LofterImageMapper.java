package com.zsxneil.bing.mappers;

import com.zsxneil.bing.model.LofterImage;

import java.util.HashMap;
import java.util.List;

public interface LofterImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    int insert(LofterImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    int insertSelective(LofterImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    LofterImage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LofterImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lofter_images
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LofterImage record);

    List<HashMap<String, Object>> selectAllImagesWithRefer();
}