package com.my.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Configuration
//@MapperScan是mybatis-spring中提供的一个注解,指定Mapper类所在的包
@MapperScan(basePackages = {"com.my.springboot.mybatis.mappers"})
public class DBConfig {
    //我们只需要配置一个数据源即可，mybatis-spring-boot-starter会自动帮我们创建SqlSessionFactory和SqlSessionTemplate

    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String dbUserName;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.driverName}")
    private String dbDriverName;
    @Value("${mybatis.mapper-locations}")
    private String mapperXMLLocation;


    @Primary
    @Bean("druidDataSource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        dataSource.setDriverClassName(dbDriverName);
        return  dataSource;

    }


    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //bean.setTypeAliasesPackage("com.my.springboot.mybatis.model");

        //支持下划线到驼峰
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
        conf.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(conf);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        //reasonable表示分页合理化，如果pageNum<=0时候会查询第一页的数据。如果pageNum>总页数，会查询最后一页的数据。
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        //params中可以定义分页属性名称，默认是pageNum和pageSize。你也可以定义其他的名称。例如 properties.setProperty(“params”, “pageNum=page;pageSize=rows;orderBy=orderBy”);
        //properties.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        System.out.println("************************pagehelper*******************************");
        try {
            //读取多个文件下的xml文件
            List<Resource> resources = new ArrayList<>();
            Resource[] resource = resourcePatternResolver.getResources(mapperXMLLocation);
            resources.addAll(Arrays.asList(resource));
            bean.setMapperLocations(resources.toArray(new Resource[]{})); //这里这样写是为了可以添加多个xmllocation
            //bean.setMapperLocations(resource);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
