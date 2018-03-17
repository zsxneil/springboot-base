package com.my.springboot.mybatis.mappers;

import com.my.springboot.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 使用了SpringBoot之后，我们一般不再将sql写在xml映射文件，而是通过相关注解直接写在方法上
 * 所有支持的注解都位于org.apache.ibatis.annotations包中
 */
public interface UserMapper  {

    @Insert("insert into user(id, username, birthday, sex, address) values (#{id}, #{username}, #{birthday}, #{sex}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User user);

    @Update("update user set username = #{username} where id = #{id}")
    int update(User user);

    @Select("SELECT * FROM USER ")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address")
    })
    List<User> selectAll();

    @Select("SELECT * FROM USER WHERE id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address")
    })
    User selectOne(int id);

    @Delete("DELETE FROM USER  WHERE id = #{id}")
    int delete(User user);
}
