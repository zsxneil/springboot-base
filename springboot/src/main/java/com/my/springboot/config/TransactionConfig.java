package com.my.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.*;

//@Configuration
//@Aspect
//@Component
//@MapperScan(basePackages = {"com.my.springboot.mybatis.mappers"})
public class TransactionConfig {



//    @Bean(name="dataSourceTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    @Bean
    public TransactionInterceptor transactionInterceptor(DataSourceTransactionManager transactionManager) {

        NameMatchTransactionAttributeSource tas = new NameMatchTransactionAttributeSource();

        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("select*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("list*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("*", "PROPAGATION_REQUIRED,-Exception, readOnly");
        tas.setProperties(properties);


        System.out.println("**************************************************************************");
        //配置组合式事务，包括声明式和注解式。默认先使用注解，注解没找到再使用声明式
        return new TransactionInterceptor(transactionManager,
                new CompositeTransactionAttributeSource(new TransactionAttributeSource[]{new AnnotationTransactionAttributeSource(), tas}));

    }

//    @Bean
    public Advisor defaultPointcutAdvisor(TransactionInterceptor transactionInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        //这里的表达式根据业务实际情况调整
        pointcut.setExpression("execution(public * com.my.springboot.mybatis.service.impl.*.*(..))");

        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }

}
