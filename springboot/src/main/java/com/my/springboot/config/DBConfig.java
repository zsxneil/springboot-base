package com.my.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@MapperScan是mybatis-spring中提供的一个注解,指定Mapper类所在的包
@MapperScan(basePackages = {"com.my.springboot.mybatis.mappers"})
public class DBConfig {
    //我们只需要配置一个数据源即可，mybatis-spring-boot-starter会自动帮我们创建SqlSessionFactory和SqlSessionTemplate
    @Primary
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=true");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return  dataSource;

    }
}
